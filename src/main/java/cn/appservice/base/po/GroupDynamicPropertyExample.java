package cn.appservice.base.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GroupDynamicPropertyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GroupDynamicPropertyExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andGroupChangeTypeIsNull() {
            addCriterion("group_change_type is null");
            return (Criteria) this;
        }

        public Criteria andGroupChangeTypeIsNotNull() {
            addCriterion("group_change_type is not null");
            return (Criteria) this;
        }

        public Criteria andGroupChangeTypeEqualTo(Short value) {
            addCriterion("group_change_type =", value, "groupChangeType");
            return (Criteria) this;
        }

        public Criteria andGroupChangeTypeNotEqualTo(Short value) {
            addCriterion("group_change_type <>", value, "groupChangeType");
            return (Criteria) this;
        }

        public Criteria andGroupChangeTypeGreaterThan(Short value) {
            addCriterion("group_change_type >", value, "groupChangeType");
            return (Criteria) this;
        }

        public Criteria andGroupChangeTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("group_change_type >=", value, "groupChangeType");
            return (Criteria) this;
        }

        public Criteria andGroupChangeTypeLessThan(Short value) {
            addCriterion("group_change_type <", value, "groupChangeType");
            return (Criteria) this;
        }

        public Criteria andGroupChangeTypeLessThanOrEqualTo(Short value) {
            addCriterion("group_change_type <=", value, "groupChangeType");
            return (Criteria) this;
        }

        public Criteria andGroupChangeTypeIn(List<Short> values) {
            addCriterion("group_change_type in", values, "groupChangeType");
            return (Criteria) this;
        }

        public Criteria andGroupChangeTypeNotIn(List<Short> values) {
            addCriterion("group_change_type not in", values, "groupChangeType");
            return (Criteria) this;
        }

        public Criteria andGroupChangeTypeBetween(Short value1, Short value2) {
            addCriterion("group_change_type between", value1, value2, "groupChangeType");
            return (Criteria) this;
        }

        public Criteria andGroupChangeTypeNotBetween(Short value1, Short value2) {
            addCriterion("group_change_type not between", value1, value2, "groupChangeType");
            return (Criteria) this;
        }

        public Criteria andGroupChangeDatetimeIsNull() {
            addCriterion("group_change_datetime is null");
            return (Criteria) this;
        }

        public Criteria andGroupChangeDatetimeIsNotNull() {
            addCriterion("group_change_datetime is not null");
            return (Criteria) this;
        }

        public Criteria andGroupChangeDatetimeEqualTo(Date value) {
            addCriterion("group_change_datetime =", value, "groupChangeDatetime");
            return (Criteria) this;
        }

        public Criteria andGroupChangeDatetimeNotEqualTo(Date value) {
            addCriterion("group_change_datetime <>", value, "groupChangeDatetime");
            return (Criteria) this;
        }

        public Criteria andGroupChangeDatetimeGreaterThan(Date value) {
            addCriterion("group_change_datetime >", value, "groupChangeDatetime");
            return (Criteria) this;
        }

        public Criteria andGroupChangeDatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("group_change_datetime >=", value, "groupChangeDatetime");
            return (Criteria) this;
        }

        public Criteria andGroupChangeDatetimeLessThan(Date value) {
            addCriterion("group_change_datetime <", value, "groupChangeDatetime");
            return (Criteria) this;
        }

        public Criteria andGroupChangeDatetimeLessThanOrEqualTo(Date value) {
            addCriterion("group_change_datetime <=", value, "groupChangeDatetime");
            return (Criteria) this;
        }

        public Criteria andGroupChangeDatetimeIn(List<Date> values) {
            addCriterion("group_change_datetime in", values, "groupChangeDatetime");
            return (Criteria) this;
        }

        public Criteria andGroupChangeDatetimeNotIn(List<Date> values) {
            addCriterion("group_change_datetime not in", values, "groupChangeDatetime");
            return (Criteria) this;
        }

        public Criteria andGroupChangeDatetimeBetween(Date value1, Date value2) {
            addCriterion("group_change_datetime between", value1, value2, "groupChangeDatetime");
            return (Criteria) this;
        }

        public Criteria andGroupChangeDatetimeNotBetween(Date value1, Date value2) {
            addCriterion("group_change_datetime not between", value1, value2, "groupChangeDatetime");
            return (Criteria) this;
        }

        public Criteria andGroupIdIsNull() {
            addCriterion("group_id is null");
            return (Criteria) this;
        }

        public Criteria andGroupIdIsNotNull() {
            addCriterion("group_id is not null");
            return (Criteria) this;
        }

        public Criteria andGroupIdEqualTo(Long value) {
            addCriterion("group_id =", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotEqualTo(Long value) {
            addCriterion("group_id <>", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThan(Long value) {
            addCriterion("group_id >", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThanOrEqualTo(Long value) {
            addCriterion("group_id >=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThan(Long value) {
            addCriterion("group_id <", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThanOrEqualTo(Long value) {
            addCriterion("group_id <=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdIn(List<Long> values) {
            addCriterion("group_id in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotIn(List<Long> values) {
            addCriterion("group_id not in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdBetween(Long value1, Long value2) {
            addCriterion("group_id between", value1, value2, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotBetween(Long value1, Long value2) {
            addCriterion("group_id not between", value1, value2, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupOwnerUidIsNull() {
            addCriterion("group_owner_uid is null");
            return (Criteria) this;
        }

        public Criteria andGroupOwnerUidIsNotNull() {
            addCriterion("group_owner_uid is not null");
            return (Criteria) this;
        }

        public Criteria andGroupOwnerUidEqualTo(Integer value) {
            addCriterion("group_owner_uid =", value, "groupOwnerUid");
            return (Criteria) this;
        }

        public Criteria andGroupOwnerUidNotEqualTo(Integer value) {
            addCriterion("group_owner_uid <>", value, "groupOwnerUid");
            return (Criteria) this;
        }

        public Criteria andGroupOwnerUidGreaterThan(Integer value) {
            addCriterion("group_owner_uid >", value, "groupOwnerUid");
            return (Criteria) this;
        }

        public Criteria andGroupOwnerUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("group_owner_uid >=", value, "groupOwnerUid");
            return (Criteria) this;
        }

        public Criteria andGroupOwnerUidLessThan(Integer value) {
            addCriterion("group_owner_uid <", value, "groupOwnerUid");
            return (Criteria) this;
        }

        public Criteria andGroupOwnerUidLessThanOrEqualTo(Integer value) {
            addCriterion("group_owner_uid <=", value, "groupOwnerUid");
            return (Criteria) this;
        }

        public Criteria andGroupOwnerUidIn(List<Integer> values) {
            addCriterion("group_owner_uid in", values, "groupOwnerUid");
            return (Criteria) this;
        }

        public Criteria andGroupOwnerUidNotIn(List<Integer> values) {
            addCriterion("group_owner_uid not in", values, "groupOwnerUid");
            return (Criteria) this;
        }

        public Criteria andGroupOwnerUidBetween(Integer value1, Integer value2) {
            addCriterion("group_owner_uid between", value1, value2, "groupOwnerUid");
            return (Criteria) this;
        }

        public Criteria andGroupOwnerUidNotBetween(Integer value1, Integer value2) {
            addCriterion("group_owner_uid not between", value1, value2, "groupOwnerUid");
            return (Criteria) this;
        }

        public Criteria andGroupLevelIsNull() {
            addCriterion("group_level is null");
            return (Criteria) this;
        }

        public Criteria andGroupLevelIsNotNull() {
            addCriterion("group_level is not null");
            return (Criteria) this;
        }

        public Criteria andGroupLevelEqualTo(Integer value) {
            addCriterion("group_level =", value, "groupLevel");
            return (Criteria) this;
        }

        public Criteria andGroupLevelNotEqualTo(Integer value) {
            addCriterion("group_level <>", value, "groupLevel");
            return (Criteria) this;
        }

        public Criteria andGroupLevelGreaterThan(Integer value) {
            addCriterion("group_level >", value, "groupLevel");
            return (Criteria) this;
        }

        public Criteria andGroupLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("group_level >=", value, "groupLevel");
            return (Criteria) this;
        }

        public Criteria andGroupLevelLessThan(Integer value) {
            addCriterion("group_level <", value, "groupLevel");
            return (Criteria) this;
        }

        public Criteria andGroupLevelLessThanOrEqualTo(Integer value) {
            addCriterion("group_level <=", value, "groupLevel");
            return (Criteria) this;
        }

        public Criteria andGroupLevelIn(List<Integer> values) {
            addCriterion("group_level in", values, "groupLevel");
            return (Criteria) this;
        }

        public Criteria andGroupLevelNotIn(List<Integer> values) {
            addCriterion("group_level not in", values, "groupLevel");
            return (Criteria) this;
        }

        public Criteria andGroupLevelBetween(Integer value1, Integer value2) {
            addCriterion("group_level between", value1, value2, "groupLevel");
            return (Criteria) this;
        }

        public Criteria andGroupLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("group_level not between", value1, value2, "groupLevel");
            return (Criteria) this;
        }

        public Criteria andGroupParentsTypeIsNull() {
            addCriterion("group_parents_type is null");
            return (Criteria) this;
        }

        public Criteria andGroupParentsTypeIsNotNull() {
            addCriterion("group_parents_type is not null");
            return (Criteria) this;
        }

        public Criteria andGroupParentsTypeEqualTo(Integer value) {
            addCriterion("group_parents_type =", value, "groupParentsType");
            return (Criteria) this;
        }

        public Criteria andGroupParentsTypeNotEqualTo(Integer value) {
            addCriterion("group_parents_type <>", value, "groupParentsType");
            return (Criteria) this;
        }

        public Criteria andGroupParentsTypeGreaterThan(Integer value) {
            addCriterion("group_parents_type >", value, "groupParentsType");
            return (Criteria) this;
        }

        public Criteria andGroupParentsTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("group_parents_type >=", value, "groupParentsType");
            return (Criteria) this;
        }

        public Criteria andGroupParentsTypeLessThan(Integer value) {
            addCriterion("group_parents_type <", value, "groupParentsType");
            return (Criteria) this;
        }

        public Criteria andGroupParentsTypeLessThanOrEqualTo(Integer value) {
            addCriterion("group_parents_type <=", value, "groupParentsType");
            return (Criteria) this;
        }

        public Criteria andGroupParentsTypeIn(List<Integer> values) {
            addCriterion("group_parents_type in", values, "groupParentsType");
            return (Criteria) this;
        }

        public Criteria andGroupParentsTypeNotIn(List<Integer> values) {
            addCriterion("group_parents_type not in", values, "groupParentsType");
            return (Criteria) this;
        }

        public Criteria andGroupParentsTypeBetween(Integer value1, Integer value2) {
            addCriterion("group_parents_type between", value1, value2, "groupParentsType");
            return (Criteria) this;
        }

        public Criteria andGroupParentsTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("group_parents_type not between", value1, value2, "groupParentsType");
            return (Criteria) this;
        }

        public Criteria andGroupChildTypeIsNull() {
            addCriterion("group_child_type is null");
            return (Criteria) this;
        }

        public Criteria andGroupChildTypeIsNotNull() {
            addCriterion("group_child_type is not null");
            return (Criteria) this;
        }

        public Criteria andGroupChildTypeEqualTo(Integer value) {
            addCriterion("group_child_type =", value, "groupChildType");
            return (Criteria) this;
        }

        public Criteria andGroupChildTypeNotEqualTo(Integer value) {
            addCriterion("group_child_type <>", value, "groupChildType");
            return (Criteria) this;
        }

        public Criteria andGroupChildTypeGreaterThan(Integer value) {
            addCriterion("group_child_type >", value, "groupChildType");
            return (Criteria) this;
        }

        public Criteria andGroupChildTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("group_child_type >=", value, "groupChildType");
            return (Criteria) this;
        }

        public Criteria andGroupChildTypeLessThan(Integer value) {
            addCriterion("group_child_type <", value, "groupChildType");
            return (Criteria) this;
        }

        public Criteria andGroupChildTypeLessThanOrEqualTo(Integer value) {
            addCriterion("group_child_type <=", value, "groupChildType");
            return (Criteria) this;
        }

        public Criteria andGroupChildTypeIn(List<Integer> values) {
            addCriterion("group_child_type in", values, "groupChildType");
            return (Criteria) this;
        }

        public Criteria andGroupChildTypeNotIn(List<Integer> values) {
            addCriterion("group_child_type not in", values, "groupChildType");
            return (Criteria) this;
        }

        public Criteria andGroupChildTypeBetween(Integer value1, Integer value2) {
            addCriterion("group_child_type between", value1, value2, "groupChildType");
            return (Criteria) this;
        }

        public Criteria andGroupChildTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("group_child_type not between", value1, value2, "groupChildType");
            return (Criteria) this;
        }

        public Criteria andGroupNameIsNull() {
            addCriterion("group_name is null");
            return (Criteria) this;
        }

        public Criteria andGroupNameIsNotNull() {
            addCriterion("group_name is not null");
            return (Criteria) this;
        }

        public Criteria andGroupNameEqualTo(String value) {
            addCriterion("group_name =", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotEqualTo(String value) {
            addCriterion("group_name <>", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThan(String value) {
            addCriterion("group_name >", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThanOrEqualTo(String value) {
            addCriterion("group_name >=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThan(String value) {
            addCriterion("group_name <", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThanOrEqualTo(String value) {
            addCriterion("group_name <=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLike(String value) {
            addCriterion("group_name like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotLike(String value) {
            addCriterion("group_name not like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameIn(List<String> values) {
            addCriterion("group_name in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotIn(List<String> values) {
            addCriterion("group_name not in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameBetween(String value1, String value2) {
            addCriterion("group_name between", value1, value2, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotBetween(String value1, String value2) {
            addCriterion("group_name not between", value1, value2, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupAnnouncementIsNull() {
            addCriterion("group_announcement is null");
            return (Criteria) this;
        }

        public Criteria andGroupAnnouncementIsNotNull() {
            addCriterion("group_announcement is not null");
            return (Criteria) this;
        }

        public Criteria andGroupAnnouncementEqualTo(String value) {
            addCriterion("group_announcement =", value, "groupAnnouncement");
            return (Criteria) this;
        }

        public Criteria andGroupAnnouncementNotEqualTo(String value) {
            addCriterion("group_announcement <>", value, "groupAnnouncement");
            return (Criteria) this;
        }

        public Criteria andGroupAnnouncementGreaterThan(String value) {
            addCriterion("group_announcement >", value, "groupAnnouncement");
            return (Criteria) this;
        }

        public Criteria andGroupAnnouncementGreaterThanOrEqualTo(String value) {
            addCriterion("group_announcement >=", value, "groupAnnouncement");
            return (Criteria) this;
        }

        public Criteria andGroupAnnouncementLessThan(String value) {
            addCriterion("group_announcement <", value, "groupAnnouncement");
            return (Criteria) this;
        }

        public Criteria andGroupAnnouncementLessThanOrEqualTo(String value) {
            addCriterion("group_announcement <=", value, "groupAnnouncement");
            return (Criteria) this;
        }

        public Criteria andGroupAnnouncementLike(String value) {
            addCriterion("group_announcement like", value, "groupAnnouncement");
            return (Criteria) this;
        }

        public Criteria andGroupAnnouncementNotLike(String value) {
            addCriterion("group_announcement not like", value, "groupAnnouncement");
            return (Criteria) this;
        }

        public Criteria andGroupAnnouncementIn(List<String> values) {
            addCriterion("group_announcement in", values, "groupAnnouncement");
            return (Criteria) this;
        }

        public Criteria andGroupAnnouncementNotIn(List<String> values) {
            addCriterion("group_announcement not in", values, "groupAnnouncement");
            return (Criteria) this;
        }

        public Criteria andGroupAnnouncementBetween(String value1, String value2) {
            addCriterion("group_announcement between", value1, value2, "groupAnnouncement");
            return (Criteria) this;
        }

        public Criteria andGroupAnnouncementNotBetween(String value1, String value2) {
            addCriterion("group_announcement not between", value1, value2, "groupAnnouncement");
            return (Criteria) this;
        }

        public Criteria andGroupDescriptionIsNull() {
            addCriterion("group_description is null");
            return (Criteria) this;
        }

        public Criteria andGroupDescriptionIsNotNull() {
            addCriterion("group_description is not null");
            return (Criteria) this;
        }

        public Criteria andGroupDescriptionEqualTo(String value) {
            addCriterion("group_description =", value, "groupDescription");
            return (Criteria) this;
        }

        public Criteria andGroupDescriptionNotEqualTo(String value) {
            addCriterion("group_description <>", value, "groupDescription");
            return (Criteria) this;
        }

        public Criteria andGroupDescriptionGreaterThan(String value) {
            addCriterion("group_description >", value, "groupDescription");
            return (Criteria) this;
        }

        public Criteria andGroupDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("group_description >=", value, "groupDescription");
            return (Criteria) this;
        }

        public Criteria andGroupDescriptionLessThan(String value) {
            addCriterion("group_description <", value, "groupDescription");
            return (Criteria) this;
        }

        public Criteria andGroupDescriptionLessThanOrEqualTo(String value) {
            addCriterion("group_description <=", value, "groupDescription");
            return (Criteria) this;
        }

        public Criteria andGroupDescriptionLike(String value) {
            addCriterion("group_description like", value, "groupDescription");
            return (Criteria) this;
        }

        public Criteria andGroupDescriptionNotLike(String value) {
            addCriterion("group_description not like", value, "groupDescription");
            return (Criteria) this;
        }

        public Criteria andGroupDescriptionIn(List<String> values) {
            addCriterion("group_description in", values, "groupDescription");
            return (Criteria) this;
        }

        public Criteria andGroupDescriptionNotIn(List<String> values) {
            addCriterion("group_description not in", values, "groupDescription");
            return (Criteria) this;
        }

        public Criteria andGroupDescriptionBetween(String value1, String value2) {
            addCriterion("group_description between", value1, value2, "groupDescription");
            return (Criteria) this;
        }

        public Criteria andGroupDescriptionNotBetween(String value1, String value2) {
            addCriterion("group_description not between", value1, value2, "groupDescription");
            return (Criteria) this;
        }

        public Criteria andGroupJoinTypeIsNull() {
            addCriterion("group_join_type is null");
            return (Criteria) this;
        }

        public Criteria andGroupJoinTypeIsNotNull() {
            addCriterion("group_join_type is not null");
            return (Criteria) this;
        }

        public Criteria andGroupJoinTypeEqualTo(Integer value) {
            addCriterion("group_join_type =", value, "groupJoinType");
            return (Criteria) this;
        }

        public Criteria andGroupJoinTypeNotEqualTo(Integer value) {
            addCriterion("group_join_type <>", value, "groupJoinType");
            return (Criteria) this;
        }

        public Criteria andGroupJoinTypeGreaterThan(Integer value) {
            addCriterion("group_join_type >", value, "groupJoinType");
            return (Criteria) this;
        }

        public Criteria andGroupJoinTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("group_join_type >=", value, "groupJoinType");
            return (Criteria) this;
        }

        public Criteria andGroupJoinTypeLessThan(Integer value) {
            addCriterion("group_join_type <", value, "groupJoinType");
            return (Criteria) this;
        }

        public Criteria andGroupJoinTypeLessThanOrEqualTo(Integer value) {
            addCriterion("group_join_type <=", value, "groupJoinType");
            return (Criteria) this;
        }

        public Criteria andGroupJoinTypeIn(List<Integer> values) {
            addCriterion("group_join_type in", values, "groupJoinType");
            return (Criteria) this;
        }

        public Criteria andGroupJoinTypeNotIn(List<Integer> values) {
            addCriterion("group_join_type not in", values, "groupJoinType");
            return (Criteria) this;
        }

        public Criteria andGroupJoinTypeBetween(Integer value1, Integer value2) {
            addCriterion("group_join_type between", value1, value2, "groupJoinType");
            return (Criteria) this;
        }

        public Criteria andGroupJoinTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("group_join_type not between", value1, value2, "groupJoinType");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}