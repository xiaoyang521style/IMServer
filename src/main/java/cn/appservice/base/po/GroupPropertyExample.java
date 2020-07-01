package cn.appservice.base.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GroupPropertyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GroupPropertyExample() {
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

        public Criteria andGroupCreaterIdIsNull() {
            addCriterion("group_creater_id is null");
            return (Criteria) this;
        }

        public Criteria andGroupCreaterIdIsNotNull() {
            addCriterion("group_creater_id is not null");
            return (Criteria) this;
        }

        public Criteria andGroupCreaterIdEqualTo(Integer value) {
            addCriterion("group_creater_id =", value, "groupCreaterId");
            return (Criteria) this;
        }

        public Criteria andGroupCreaterIdNotEqualTo(Integer value) {
            addCriterion("group_creater_id <>", value, "groupCreaterId");
            return (Criteria) this;
        }

        public Criteria andGroupCreaterIdGreaterThan(Integer value) {
            addCriterion("group_creater_id >", value, "groupCreaterId");
            return (Criteria) this;
        }

        public Criteria andGroupCreaterIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("group_creater_id >=", value, "groupCreaterId");
            return (Criteria) this;
        }

        public Criteria andGroupCreaterIdLessThan(Integer value) {
            addCriterion("group_creater_id <", value, "groupCreaterId");
            return (Criteria) this;
        }

        public Criteria andGroupCreaterIdLessThanOrEqualTo(Integer value) {
            addCriterion("group_creater_id <=", value, "groupCreaterId");
            return (Criteria) this;
        }

        public Criteria andGroupCreaterIdIn(List<Integer> values) {
            addCriterion("group_creater_id in", values, "groupCreaterId");
            return (Criteria) this;
        }

        public Criteria andGroupCreaterIdNotIn(List<Integer> values) {
            addCriterion("group_creater_id not in", values, "groupCreaterId");
            return (Criteria) this;
        }

        public Criteria andGroupCreaterIdBetween(Integer value1, Integer value2) {
            addCriterion("group_creater_id between", value1, value2, "groupCreaterId");
            return (Criteria) this;
        }

        public Criteria andGroupCreaterIdNotBetween(Integer value1, Integer value2) {
            addCriterion("group_creater_id not between", value1, value2, "groupCreaterId");
            return (Criteria) this;
        }

        public Criteria andGroupCreateDatetimeIsNull() {
            addCriterion("group_create_datetime is null");
            return (Criteria) this;
        }

        public Criteria andGroupCreateDatetimeIsNotNull() {
            addCriterion("group_create_datetime is not null");
            return (Criteria) this;
        }

        public Criteria andGroupCreateDatetimeEqualTo(Date value) {
            addCriterion("group_create_datetime =", value, "groupCreateDatetime");
            return (Criteria) this;
        }

        public Criteria andGroupCreateDatetimeNotEqualTo(Date value) {
            addCriterion("group_create_datetime <>", value, "groupCreateDatetime");
            return (Criteria) this;
        }

        public Criteria andGroupCreateDatetimeGreaterThan(Date value) {
            addCriterion("group_create_datetime >", value, "groupCreateDatetime");
            return (Criteria) this;
        }

        public Criteria andGroupCreateDatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("group_create_datetime >=", value, "groupCreateDatetime");
            return (Criteria) this;
        }

        public Criteria andGroupCreateDatetimeLessThan(Date value) {
            addCriterion("group_create_datetime <", value, "groupCreateDatetime");
            return (Criteria) this;
        }

        public Criteria andGroupCreateDatetimeLessThanOrEqualTo(Date value) {
            addCriterion("group_create_datetime <=", value, "groupCreateDatetime");
            return (Criteria) this;
        }

        public Criteria andGroupCreateDatetimeIn(List<Date> values) {
            addCriterion("group_create_datetime in", values, "groupCreateDatetime");
            return (Criteria) this;
        }

        public Criteria andGroupCreateDatetimeNotIn(List<Date> values) {
            addCriterion("group_create_datetime not in", values, "groupCreateDatetime");
            return (Criteria) this;
        }

        public Criteria andGroupCreateDatetimeBetween(Date value1, Date value2) {
            addCriterion("group_create_datetime between", value1, value2, "groupCreateDatetime");
            return (Criteria) this;
        }

        public Criteria andGroupCreateDatetimeNotBetween(Date value1, Date value2) {
            addCriterion("group_create_datetime not between", value1, value2, "groupCreateDatetime");
            return (Criteria) this;
        }

        public Criteria andGroupStatuIsNull() {
            addCriterion("group_statu is null");
            return (Criteria) this;
        }

        public Criteria andGroupStatuIsNotNull() {
            addCriterion("group_statu is not null");
            return (Criteria) this;
        }

        public Criteria andGroupStatuEqualTo(Integer value) {
            addCriterion("group_statu =", value, "groupStatu");
            return (Criteria) this;
        }

        public Criteria andGroupStatuNotEqualTo(Integer value) {
            addCriterion("group_statu <>", value, "groupStatu");
            return (Criteria) this;
        }

        public Criteria andGroupStatuGreaterThan(Integer value) {
            addCriterion("group_statu >", value, "groupStatu");
            return (Criteria) this;
        }

        public Criteria andGroupStatuGreaterThanOrEqualTo(Integer value) {
            addCriterion("group_statu >=", value, "groupStatu");
            return (Criteria) this;
        }

        public Criteria andGroupStatuLessThan(Integer value) {
            addCriterion("group_statu <", value, "groupStatu");
            return (Criteria) this;
        }

        public Criteria andGroupStatuLessThanOrEqualTo(Integer value) {
            addCriterion("group_statu <=", value, "groupStatu");
            return (Criteria) this;
        }

        public Criteria andGroupStatuIn(List<Integer> values) {
            addCriterion("group_statu in", values, "groupStatu");
            return (Criteria) this;
        }

        public Criteria andGroupStatuNotIn(List<Integer> values) {
            addCriterion("group_statu not in", values, "groupStatu");
            return (Criteria) this;
        }

        public Criteria andGroupStatuBetween(Integer value1, Integer value2) {
            addCriterion("group_statu between", value1, value2, "groupStatu");
            return (Criteria) this;
        }

        public Criteria andGroupStatuNotBetween(Integer value1, Integer value2) {
            addCriterion("group_statu not between", value1, value2, "groupStatu");
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