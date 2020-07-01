package cn.appservice.utils;

import cn.appservice.base.mapper.UserMapper;
import cn.appservice.base.po.User;
import cn.appservice.common.Response;
import cn.appservice.entities.IMMessage;
import cn.appservice.mapper.UserSettingMapper;
import cn.appservice.po.AddMeSetting;
import cn.appservice.po.UserSetting;
import cn.appservice.serverimpl.*;
import cn.appservice.servers.*;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * [com.appservice.utils desc]
 *
 * @author yangkun[Email:vectormail@163.com] 2018/4/16
 */
public class MessageUtils {
    private static Logger logger = Logger.getLogger(MessageUtils.class);
    private static UserServer us = new UserServerImpl();
    private static ContactsServer contactsServer = new ContactsServerImpl();
    private static MessageServer ms = new MessageServerImpl();
    private static GroupServer groupServer = new GroupServerImpl();
    private static ChannelServer cs = new ChannelServerImpl();
    private static FriendServer fs = new FriendServerImpl();
    private static JPushServer js = new JPushServerImpl();


    /**
     * [描述： deal loginHander]
     *
     * @param ctx
     * @param m
     */
    public static void loginHander(ChannelHandlerContext ctx, IMMessage m) {
        logger.info("loginHander => " + m);
        Response response;
        try {
            response = us.userLogin(ctx, m);

            if (response.isXeach()) {
                HashMap map = (HashMap) response.getResult();
                Integer userId = (int) map.get("userId");
                User user = new User();
                user.setId(userId);

                UserSetting userSetting = us.getUserSetting(userId);
                AddMeSetting addMeSetting = us.getAddMeSetting(userId);
                map.put("message_notif",userSetting.getMessage_notif());
                map.put("show_msgDetail",userSetting.getShow_msgDetail());
                map.put("open_phone",addMeSetting.getOpen_code());
                map.put("open_code",addMeSetting.getOpen_phone());
                response.setResult(map);
                ChannelHandlerContext oldctx = SocketUtils.getInstance().getCtx(userId);

                if (oldctx != null) {
                        Response oldResponse = new Response();
                        oldResponse.setXeach(true);
                        oldResponse.setAction("logout");
                        oldResponse.setMessage("有另一个用户在使用该账号");
                        SocketUtils.getInstance().sendToContext(oldctx, oldResponse);
                        SocketUtils.getInstance().removeCtx(userId);
                }

                SocketUtils.getInstance().addCtx(ctx, userId);
                SocketUtils.getInstance().sendToContext(ctx, response);
            } else {
                ctx.close();
                ctx.channel().close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            ctx.close();
            ctx.channel().close();
        }
    }


    /**
     * [描述： deal getContactHander]
     *
     * @param ctx
     * @param m
     */

    public static void getContactHander(ChannelHandlerContext ctx, IMMessage m) {
        try {
            Response response = contactsServer.getContacts(ctx, m);
            SocketUtils.getInstance().sendToContext(ctx, response);
        } catch (Exception e) {
            e.printStackTrace();
            ctx.close();
            ctx.channel().close();
        }
    }


    /**
     * [描述： deal readHistroyMessageHandel]
     *
     * @param ctx
     * @param m
     */

    public static void readHistroyMessageHandel(ChannelHandlerContext ctx, IMMessage m) {

        try {
            Response response = ms.readHistroyMessage(m);
            SocketUtils.getInstance().sendToContext(ctx, response);
        } catch (Exception e) {
            e.printStackTrace();
            ctx.close();
            ctx.channel().close();
        }
    }


    /**
     * [描述： deal chatHandel]
     *
     * @param ctx
     * @param m
     */
    public static void chatHandel(ChannelHandlerContext ctx, IMMessage m) {
        Response response = new Response();
        if (m.getIsGroup() == 1) {
            try {
                ms.saveMessage(m);
                m.setGroupInfo(groupServer.selectGroupInfoBy(m.getRoomId()));
                List<Integer> userIds = groupServer.getGroupUsersId(Integer.parseInt(m.getRoomId()));
                m.setUserIds(userIds);
                response.setResult(m);
                response.setXeach(true);
                response.setAction("chat");
                response.setMessage("new message");
                for (int i = 0; i < userIds.size() ; i++) {
                    if (userIds.get(i) != m.getUserId()) {
                        SocketUtils.getInstance().sendToContext(response, userIds.get(i));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                ctx.close();
                ctx.channel().close();
            }
        } else {
            try {
                boolean isfriend = fs.checkFriend(m);
                if (isfriend) {
                    ms.saveMessage(m);
                    response.setResult(m);
                    response.setXeach(true);
                    response.setAction("chat");
                    response.setMessage("new message");
                    SocketUtils.getInstance().sendToContext(response, m.getFriendId());
                } else {
                    m.setErrorMsg("not friend,fail!!!");
                    m.setErrorCode(2001);
                    response.setXeach(true);
                    response.setMessage("消息发送失败，不是好友");
                    response.setAction("chat");
                    SocketUtils.getInstance().sendToContext(ctx, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
                ctx.close();
                ctx.channel().close();
            }
        }
    }


    /**
     * [描述： deal iReadHandel]
     *
     * @param ctx
     * @param m
     */
    private static void iReadHandel(ChannelHandlerContext ctx, IMMessage m) {
        try {
            ms.readMessage(m);
        } catch (Exception e) {
            e.printStackTrace();
            ctx.close();
            ctx.channel().close();
        }
    }

    /**
     * [描述： deal beyHandel]
     *
     * @param ctx
     * @param m
     */
    private static void beyHandel(ChannelHandlerContext ctx, IMMessage m) {
        //SocketUtils.getInstance().removeCtx(ctx);
    }


    /**
     * [描述： deal matchPhoneNumberHandel]
     *
     * @param ctx
     * @param m
     */
    private static void matchPhoneNumberHandel(ChannelHandlerContext ctx, IMMessage m) {
        try {
            Response response = fs.matchPhoneNumber(m);
            SocketUtils.getInstance().sendToContext(ctx, response);
        } catch (Exception e) {
            e.printStackTrace();
            ctx.close();
            ctx.channel().close();
        }
    }

    /**
     * [描述： deal searchFriendsHandel]
     *
     * @param ctx
     * @param m
     */
    private static void searchFriendsHandel(ChannelHandlerContext ctx, IMMessage m) {
        try {
            Response response = fs.searchFriendsMessage(m);
            SocketUtils.getInstance().sendToContext(ctx, response);
        } catch (Exception e) {
            e.printStackTrace();
            ctx.close();
            ctx.channel().close();
        }
    }

    /**
     * [描述： deal manageFriendHandel]
     *
     * @param ctx
     * @param m
     */
    private static void manageFriendHandel(ChannelHandlerContext ctx, IMMessage m) throws  Exception{
        try {
           AddMeSetting addMeSetting = us.getAddMeSetting( m.getFriendId());

           if (addMeSetting.getOpen_code() == 0 && m.getType() == 5) {
               Response response = new Response();
               response.setXeach(false);
               response.setResult("对方没有打开扫二维码添加好友");
               SocketUtils.getInstance().sendToContext(ctx, response);
           }else if (addMeSetting.getOpen_phone() == 0 && m.getType() == 1) {
               Response response = new Response();
               response.setXeach(false);
               response.setResult("对方没有打开通过手机号添加好友功能");
               SocketUtils.getInstance().sendToContext(ctx, response);
           }else {
               boolean res = fs.checkFriend(m);
               Response response = null;
               if ((m.getType() == 5 || m.getType() == 1) && res) {
                   response.setMessage("已经发送好友请求");
                   response.setAction("manageFriend");
                   response.setXeach(false);
                   response.setResult("");
                   SocketUtils.getInstance().sendToContext(ctx, response);
                   return;
               }
               response = fs.manageFriend(m);
               if (m.getType() == 1) {
                   SocketUtils.getInstance().sendToContext(response, m.getFriendId());
               }
               if (m.getType() == 2) {
                   SqlSession session = MyBatisUtil.getSession();
                   UserMapper userMapper = session.getMapper(UserMapper.class);
                   User user = userMapper.selectByPrimaryKey(m.getUserId());
                   User fu =  userMapper.selectByPrimaryKey(m.getFriendId());
                   session.close();

                   if (m.getUserId() == user.getId()) {
                       Map map = (Map) response.getResult();
                       map.put("username",user.getUsername());
                       map.put("icon",user.getIcon());
                       SocketUtils.getInstance().sendToContext(response, m.getFriendId());
                   }
                   if (m.getFriendId() == fu.getId()){
                       Map map = (Map) response.getResult();
                       map.put("username",fu.getUsername());
                       map.put("icon",fu.getIcon());
                       SocketUtils.getInstance().sendToContext(ctx, response);
                   }
               }
               if (m.getType() == 3) {

               }
               if (m.getType() == 4) {

               }
               if (m.getType() == 5) {
                   SqlSession session = MyBatisUtil.getSession();
                   UserMapper userMapper = session.getMapper(UserMapper.class);
                   User user = userMapper.selectByPrimaryKey(m.getUserId());
                   User fu =  userMapper.selectByPrimaryKey(m.getFriendId());
                   session.close();
                   if (m.getUserId() == user.getId()) {
                       Map map = (Map) response.getResult();
                       map.put("username",user.getUsername());
                       map.put("icon",user.getIcon());
                       SocketUtils.getInstance().sendToContext(response, m.getFriendId());
                   }
                   if (m.getFriendId() == fu.getId()){
                       Map map = (Map) response.getResult();
                       map.put("username",fu.getUsername());
                       map.put("icon",fu.getIcon());
                       SocketUtils.getInstance().sendToContext(ctx, response);
                   }
               }
           }

        } catch (Exception e) {
            e.printStackTrace();
            ctx.close();
            ctx.channel().close();
        }
    }

    /**
     * [描述： deal creatGroupHandel]
     *
     * @param ctx
     * @param m
     */
    private static void creatGroupHandel(ChannelHandlerContext ctx, IMMessage m) {
        try {
            Response response = groupServer.creatGroup(m);
            SocketUtils.getInstance().sendToContext(ctx, response);
        } catch (Exception e) {
            e.printStackTrace();
            ctx.close();
            ctx.channel().close();
        }
    }

    /**
     * [描述： deal enterRoomHandel]
     *
     * @param ctx
     * @param m
     */
    private static void enterRoomHandel(ChannelHandlerContext ctx, IMMessage m) {
        if (m.getIsGroup() == 1) {
            try {
                Response response = groupServer.enterGroupRoom(m);
                SocketUtils.getInstance().sendToContext(ctx, response);
            } catch (Exception e) {
                e.printStackTrace();
                ctx.close();
                ctx.channel().close();
            }
        }
    }

    /**
     * [描述： deal changeFriendsNotesHandel]
     *
     * @param ctx
     * @param m
     */
    private static void changeFriendsNotesHandel(ChannelHandlerContext ctx, IMMessage m) {
        try {
            Response response = fs.changeFriendsNotes(m);
            SocketUtils.getInstance().sendToContext(ctx, response);
        } catch (Exception e) {
            e.printStackTrace();
            ctx.close();
            ctx.channel().close();
        }
    }

    /**
     * [描述： deal updateGroupNameHandel]
     *
     * @param ctx
     * @param m
     */
    private static void updateGroupNameHandel(ChannelHandlerContext ctx, IMMessage m) {
        try {
            Response response = groupServer.updateGroupName(m);
            sendToGroup(m,response);
        } catch (Exception e) {
            e.printStackTrace();
            ctx.close();
            ctx.channel().close();
        }
    }

    /**
     * [描述： deal joinGroupHandel]
     *
     * @param ctx
     * @param m
     */
    private static void joinGroupHandel(ChannelHandlerContext ctx, IMMessage m) {
        try {
            Response response = groupServer.joinGroup(m);
            sendToGroup(m,response);
        } catch (Exception e) {
            e.printStackTrace();
            ctx.close();
            ctx.channel().close();
        }
    }

    /**
     * [描述： deal registerJPushHandel]
     *
     * @param ctx
     * @param m
     */
    private static void registerJPushHandel(ChannelHandlerContext ctx, IMMessage m) {
        try {
            Response response = js.deviceTokenAndUserHandel(m);
            SocketUtils.getInstance().sendToContext(ctx,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * [描述： deal quitGroupHandel]
     *
     * @param ctx
     * @param m
     */
    private static void quitGroupHandel(ChannelHandlerContext ctx, IMMessage m) {
        try {
            Response response = groupServer.quitGroup(m);
            sendToGroup(m,response);
        } catch (Exception e) {
            e.printStackTrace();
            ctx.close();
            ctx.channel().close();
        }
    }


    /**
     * [描述： deal deleteGroupUserHandel]
     *
     * @param ctx
     * @param m
     */
    private static void deleteGroupUserHandel(ChannelHandlerContext ctx, IMMessage m) {
        try {
            Response response = groupServer.deleteGroupUserHandel(m);
            if (response.isXeach()) {
                sendToGroup(m,response);
            }else {
                SocketUtils.getInstance().sendToContext(ctx,response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            ctx.close();
            ctx.channel().close();
        }
    }


    /**
     * [描述： deal callbackMsg]
     *
     * @param ctx
     * @param m
     */
    private static void callbackMsgHandel(ChannelHandlerContext ctx, IMMessage m) {

        try {

            Response response = new Response();
            if (m.getIsGroup() == 1) {
                try {
                    ms.callBackMsg(m);
                    m.setGroupInfo(groupServer.selectGroupInfoBy(m.getRoomId()));
                    List<Integer> userIds = groupServer.getGroupUsersId(Integer.parseInt(m.getRoomId()));
                    m.setUserIds(userIds);
                    response.setResult(m);
                    response.setXeach(true);
                    response.setAction("callBackMsg");
                    response.setMessage("callBack message");
                    sendToGroup(m,response);
                } catch (Exception e) {
                    e.printStackTrace();
                    ctx.close();
                    ctx.channel().close();
                }
            } else {
                try {
                    boolean isfriend = fs.checkFriend(m);
                    if (isfriend) {
                        ms.callBackMsg(m);
                        response.setResult(m);
                        response.setXeach(true);
                        response.setAction("callBackMsg");
                        response.setMessage("callBackMsg");
                        SocketUtils.getInstance().sendToContext(response, m.getFriendId());
                    } else {
                        m.setErrorMsg("not friend,fail!!!");
                        m.setErrorCode(2001);
                        response.setXeach(true);
                        response.setMessage("callBack message");
                        response.setAction("chat");
                        SocketUtils.getInstance().sendToContext(ctx, response);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    ctx.close();
                    ctx.channel().close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * [描述： deal  callHandel]
     *
     * @param ctx
     * @param m
     */
    private static void callHandel(ChannelHandlerContext ctx, IMMessage m) {
        Response response = new Response();
        try {
            m.setUserId(m.getCallUserID());
            m.setFriendId(m.getRemoteUserID());
            boolean isfriend = fs.checkFriend(m);

            if (isfriend) {
                response.setResult(m);
                response.setXeach(true);
                response.setAction("call");
                response.setMessage("call message");
                SocketUtils.getInstance().sendToContext(response, m.getRemoteUserID());
            } else {
                m.setErrorMsg("not friend,fail!!!");
                m.setErrorCode(2001);
                response.setXeach(true);
                response.setMessage("消息发送失败，不是好友");
                response.setAction("call");
                SocketUtils.getInstance().sendToContext(ctx, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ctx.close();
            ctx.channel().close();
        }
    }

    /**
     * [描述： deal exchangeMaster]
     *
     * @param ctx
     * @param m
     */

    private static void exchangeMaster(ChannelHandlerContext ctx, IMMessage m){
        try {
            Response response = groupServer.exchangeMaster(m);
            if (response.isXeach()) {
                sendToGroup(m,response);
            }else {
                SocketUtils.getInstance().sendToContext(ctx,response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            ctx.close();
            ctx.channel().close();
        }
    }

    /**
     * [描述： deal request]
     *
     * @param ctx
     * @param msg
     * @author yangkun[Email:vectormail@163.com] 2018/5/28
     */
    public static void msgHandler(ChannelHandlerContext ctx, String msg) throws Exception{
        IMMessage m = JSONObject.parseObject(msg, IMMessage.class);
        if (null == m) return;
        switch (m.getAction().toLowerCase()) {
            case "login":
                loginHander(ctx, m);
                break;
            case "getcontactlist":
                getContactHander(ctx, m);
                break;
            case "readhistroymessage":
                readHistroyMessageHandel(ctx, m);
                break;
            case "chat":
                chatHandel(ctx, m);

                break;
            case "iread":
                iReadHandel(ctx, m);
                break;

            case "bey":
                beyHandel(ctx, m);
                break;

            case "matchphonenumber":
                matchPhoneNumberHandel(ctx, m);

                break;

            case "searchfriends":
                searchFriendsHandel(ctx, m);

                break;

            case "managefriend":
                manageFriendHandel(ctx, m);

                break;

            case "creatgroup":
                creatGroupHandel(ctx, m);
                break;

            case "enterroom":
                enterRoomHandel(ctx, m);
                break;

            case "changefriendsnotes":
                changeFriendsNotesHandel(ctx, m);
                break;

            case "updategroupname":
                updateGroupNameHandel(ctx, m);
                break;

            case "joingroup":
                joinGroupHandel(ctx,m);
                break;

            case "quitgroup":
                quitGroupHandel(ctx,m);
                break;

            case "deletegroupuser":
                deleteGroupUserHandel(ctx,m);
                break;
            case "registerjpush":
                registerJPushHandel(ctx,m);
                break;

            case "callbackmsg":
                callbackMsgHandel(ctx,m);

            break;
            case "call":
                callHandel(ctx,m);
                break;
            case "live":
                pingHandler(ctx, m);
                break;
            case "appgroupshare":
            chatHandel(ctx,m);
                break;
            case "exchangemaster":
                exchangeMaster(ctx,m);
                break;
        }
    }

    /**
     *群信息广播
     */

    private static void sendToGroup(IMMessage messageBean, Response response) throws Exception {
        List<Integer> userIds = groupServer.getGroupUsersId(Integer.parseInt(messageBean.getRoomId()));
        for (int i = userIds.size() - 1; i >= 0; i--) {
            SocketUtils.getInstance().sendToContext(response, userIds.get(i));
        }

        if (messageBean.getAction().equals("deleteGroupUser")) {
            SocketUtils.getInstance().sendToContext(response, messageBean.getUserId());
        }
    }

    public static void pingHandler(ChannelHandlerContext ctx, IMMessage m) {

    }


}
