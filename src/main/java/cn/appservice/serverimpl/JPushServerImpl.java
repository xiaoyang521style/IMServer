package cn.appservice.serverimpl;

import cn.appservice.base.mapper.DeviceinfoMapper;
import cn.appservice.base.mapper.UserdeviceMapper;
import cn.appservice.base.po.Deviceinfo;
import cn.appservice.base.po.DeviceinfoExample;
import cn.appservice.base.po.Userdevice;
import cn.appservice.base.po.UserdeviceExample;
import cn.appservice.common.Response;
import cn.appservice.entities.IMMessage;
import cn.appservice.handler.HandleMessage;
import cn.appservice.servers.JPushServer;
import cn.appservice.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class JPushServerImpl extends HandleMessage implements JPushServer {

//    private static DeviceinfoMapper deviceinfoMapper;
//
//    private static UserdeviceMapper userdeviceMapper;

    private  Object pushRegist (String deviceToken,
                                int deviceModel,
                                String systemVersion,
                                String deviceName) throws  Exception {
        SqlSession session = MyBatisUtil.getSession();
        DeviceinfoMapper deviceinfoMapper = session.getMapper(DeviceinfoMapper.class);

        DeviceinfoExample example = new DeviceinfoExample();
        DeviceinfoExample.Criteria criteria = example.createCriteria();
        criteria.andDevicetokenEqualTo(deviceToken);
        List<Deviceinfo> list = deviceinfoMapper.selectByExample(example);
        Response response = new Response();
        Deviceinfo deviceinfo = new Deviceinfo();
        if (list.size()==0){
            deviceinfo.setDevicemodel(deviceModel);
            deviceinfo.setDevicename(deviceName);
            deviceinfo.setDevicetoken(deviceToken);
            deviceinfo.setSystemversion(systemVersion);
            int id = deviceinfoMapper.insert(deviceinfo);
            session.commit();
            deviceinfo.setId(id);
            response.setMessage("新注册");
            response.setXeach(true);
        } else {
            deviceinfo = list.get(0);
        }
        session.close();
        return  deviceinfo;
    }


    @Override
    public Response deviceTokenAndUserHandel(IMMessage m) throws Exception {
        SqlSession session = MyBatisUtil.getSession();
        UserdeviceMapper userdeviceMapper = session.getMapper(UserdeviceMapper.class);

        Deviceinfo deviceinfo = (Deviceinfo) pushRegist(m.getDeviceToken(),
                m.getDeviceModel(),
                m.getSystemVersion(),
                m.getDeviceName());
        Response response = new Response();
        //查出deviceinfoId
        int deviceinfoId = deviceinfo.getId();
        //删除
        UserdeviceExample userExample = new UserdeviceExample();
        UserdeviceExample.Criteria usercriteria = userExample.createCriteria();
        usercriteria.andUseridEqualTo(m.getUserId());
        List<Userdevice> userList= userdeviceMapper.selectByExample(userExample);


        for (int i = 0 ; i< userList.size(); i++) {
            Userdevice userdevice = userList.get(i);
            userdeviceMapper.deleteByPrimaryKey(userdevice.getId());
            session.commit();

        }


        UserdeviceExample deviceExample = new UserdeviceExample();
        UserdeviceExample.Criteria devicecriteria = deviceExample.createCriteria();
        devicecriteria.andDeviceidEqualTo(deviceinfoId);

        List<Userdevice> deviceList= userdeviceMapper.selectByExample(deviceExample);

        for (int i = 0 ; i< deviceList.size(); i++) {
            Userdevice userdevice = deviceList.get(i);
            userdeviceMapper.deleteByPrimaryKey(userdevice.getId());
        }


        //插入
        Userdevice userdevice = new Userdevice();
        userdevice.setDeviceid(deviceinfoId);
        userdevice.setUserid(m.getUserId());
        userdeviceMapper.insert(userdevice);
        session.commit();

        session.close();

        response.setResult(userdevice);
        response.setAction("registerJPush");
        response.setMessage("注册推送");
        response.setXeach(true);

        return response;
    }
}
