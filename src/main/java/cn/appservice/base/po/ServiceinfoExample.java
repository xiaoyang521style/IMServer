package cn.appservice.base.po;

import java.util.ArrayList;
import java.util.List;

public class ServiceinfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ServiceinfoExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andServeripIsNull() {
            addCriterion("serverIP is null");
            return (Criteria) this;
        }

        public Criteria andServeripIsNotNull() {
            addCriterion("serverIP is not null");
            return (Criteria) this;
        }

        public Criteria andServeripEqualTo(String value) {
            addCriterion("serverIP =", value, "serverip");
            return (Criteria) this;
        }

        public Criteria andServeripNotEqualTo(String value) {
            addCriterion("serverIP <>", value, "serverip");
            return (Criteria) this;
        }

        public Criteria andServeripGreaterThan(String value) {
            addCriterion("serverIP >", value, "serverip");
            return (Criteria) this;
        }

        public Criteria andServeripGreaterThanOrEqualTo(String value) {
            addCriterion("serverIP >=", value, "serverip");
            return (Criteria) this;
        }

        public Criteria andServeripLessThan(String value) {
            addCriterion("serverIP <", value, "serverip");
            return (Criteria) this;
        }

        public Criteria andServeripLessThanOrEqualTo(String value) {
            addCriterion("serverIP <=", value, "serverip");
            return (Criteria) this;
        }

        public Criteria andServeripLike(String value) {
            addCriterion("serverIP like", value, "serverip");
            return (Criteria) this;
        }

        public Criteria andServeripNotLike(String value) {
            addCriterion("serverIP not like", value, "serverip");
            return (Criteria) this;
        }

        public Criteria andServeripIn(List<String> values) {
            addCriterion("serverIP in", values, "serverip");
            return (Criteria) this;
        }

        public Criteria andServeripNotIn(List<String> values) {
            addCriterion("serverIP not in", values, "serverip");
            return (Criteria) this;
        }

        public Criteria andServeripBetween(String value1, String value2) {
            addCriterion("serverIP between", value1, value2, "serverip");
            return (Criteria) this;
        }

        public Criteria andServeripNotBetween(String value1, String value2) {
            addCriterion("serverIP not between", value1, value2, "serverip");
            return (Criteria) this;
        }

        public Criteria andServerportIsNull() {
            addCriterion("serverPort is null");
            return (Criteria) this;
        }

        public Criteria andServerportIsNotNull() {
            addCriterion("serverPort is not null");
            return (Criteria) this;
        }

        public Criteria andServerportEqualTo(Integer value) {
            addCriterion("serverPort =", value, "serverport");
            return (Criteria) this;
        }

        public Criteria andServerportNotEqualTo(Integer value) {
            addCriterion("serverPort <>", value, "serverport");
            return (Criteria) this;
        }

        public Criteria andServerportGreaterThan(Integer value) {
            addCriterion("serverPort >", value, "serverport");
            return (Criteria) this;
        }

        public Criteria andServerportGreaterThanOrEqualTo(Integer value) {
            addCriterion("serverPort >=", value, "serverport");
            return (Criteria) this;
        }

        public Criteria andServerportLessThan(Integer value) {
            addCriterion("serverPort <", value, "serverport");
            return (Criteria) this;
        }

        public Criteria andServerportLessThanOrEqualTo(Integer value) {
            addCriterion("serverPort <=", value, "serverport");
            return (Criteria) this;
        }

        public Criteria andServerportIn(List<Integer> values) {
            addCriterion("serverPort in", values, "serverport");
            return (Criteria) this;
        }

        public Criteria andServerportNotIn(List<Integer> values) {
            addCriterion("serverPort not in", values, "serverport");
            return (Criteria) this;
        }

        public Criteria andServerportBetween(Integer value1, Integer value2) {
            addCriterion("serverPort between", value1, value2, "serverport");
            return (Criteria) this;
        }

        public Criteria andServerportNotBetween(Integer value1, Integer value2) {
            addCriterion("serverPort not between", value1, value2, "serverport");
            return (Criteria) this;
        }

        public Criteria andSocketlivetimeIsNull() {
            addCriterion("socketLiveTime is null");
            return (Criteria) this;
        }

        public Criteria andSocketlivetimeIsNotNull() {
            addCriterion("socketLiveTime is not null");
            return (Criteria) this;
        }

        public Criteria andSocketlivetimeEqualTo(Integer value) {
            addCriterion("socketLiveTime =", value, "socketlivetime");
            return (Criteria) this;
        }

        public Criteria andSocketlivetimeNotEqualTo(Integer value) {
            addCriterion("socketLiveTime <>", value, "socketlivetime");
            return (Criteria) this;
        }

        public Criteria andSocketlivetimeGreaterThan(Integer value) {
            addCriterion("socketLiveTime >", value, "socketlivetime");
            return (Criteria) this;
        }

        public Criteria andSocketlivetimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("socketLiveTime >=", value, "socketlivetime");
            return (Criteria) this;
        }

        public Criteria andSocketlivetimeLessThan(Integer value) {
            addCriterion("socketLiveTime <", value, "socketlivetime");
            return (Criteria) this;
        }

        public Criteria andSocketlivetimeLessThanOrEqualTo(Integer value) {
            addCriterion("socketLiveTime <=", value, "socketlivetime");
            return (Criteria) this;
        }

        public Criteria andSocketlivetimeIn(List<Integer> values) {
            addCriterion("socketLiveTime in", values, "socketlivetime");
            return (Criteria) this;
        }

        public Criteria andSocketlivetimeNotIn(List<Integer> values) {
            addCriterion("socketLiveTime not in", values, "socketlivetime");
            return (Criteria) this;
        }

        public Criteria andSocketlivetimeBetween(Integer value1, Integer value2) {
            addCriterion("socketLiveTime between", value1, value2, "socketlivetime");
            return (Criteria) this;
        }

        public Criteria andSocketlivetimeNotBetween(Integer value1, Integer value2) {
            addCriterion("socketLiveTime not between", value1, value2, "socketlivetime");
            return (Criteria) this;
        }

        public Criteria andSslenableIsNull() {
            addCriterion("sslEnable is null");
            return (Criteria) this;
        }

        public Criteria andSslenableIsNotNull() {
            addCriterion("sslEnable is not null");
            return (Criteria) this;
        }

        public Criteria andSslenableEqualTo(Boolean value) {
            addCriterion("sslEnable =", value, "sslenable");
            return (Criteria) this;
        }

        public Criteria andSslenableNotEqualTo(Boolean value) {
            addCriterion("sslEnable <>", value, "sslenable");
            return (Criteria) this;
        }

        public Criteria andSslenableGreaterThan(Boolean value) {
            addCriterion("sslEnable >", value, "sslenable");
            return (Criteria) this;
        }

        public Criteria andSslenableGreaterThanOrEqualTo(Boolean value) {
            addCriterion("sslEnable >=", value, "sslenable");
            return (Criteria) this;
        }

        public Criteria andSslenableLessThan(Boolean value) {
            addCriterion("sslEnable <", value, "sslenable");
            return (Criteria) this;
        }

        public Criteria andSslenableLessThanOrEqualTo(Boolean value) {
            addCriterion("sslEnable <=", value, "sslenable");
            return (Criteria) this;
        }

        public Criteria andSslenableIn(List<Boolean> values) {
            addCriterion("sslEnable in", values, "sslenable");
            return (Criteria) this;
        }

        public Criteria andSslenableNotIn(List<Boolean> values) {
            addCriterion("sslEnable not in", values, "sslenable");
            return (Criteria) this;
        }

        public Criteria andSslenableBetween(Boolean value1, Boolean value2) {
            addCriterion("sslEnable between", value1, value2, "sslenable");
            return (Criteria) this;
        }

        public Criteria andSslenableNotBetween(Boolean value1, Boolean value2) {
            addCriterion("sslEnable not between", value1, value2, "sslenable");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyIsNull() {
            addCriterion("privateKey is null");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyIsNotNull() {
            addCriterion("privateKey is not null");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyEqualTo(String value) {
            addCriterion("privateKey =", value, "privatekey");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyNotEqualTo(String value) {
            addCriterion("privateKey <>", value, "privatekey");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyGreaterThan(String value) {
            addCriterion("privateKey >", value, "privatekey");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyGreaterThanOrEqualTo(String value) {
            addCriterion("privateKey >=", value, "privatekey");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyLessThan(String value) {
            addCriterion("privateKey <", value, "privatekey");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyLessThanOrEqualTo(String value) {
            addCriterion("privateKey <=", value, "privatekey");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyLike(String value) {
            addCriterion("privateKey like", value, "privatekey");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyNotLike(String value) {
            addCriterion("privateKey not like", value, "privatekey");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyIn(List<String> values) {
            addCriterion("privateKey in", values, "privatekey");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyNotIn(List<String> values) {
            addCriterion("privateKey not in", values, "privatekey");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyBetween(String value1, String value2) {
            addCriterion("privateKey between", value1, value2, "privatekey");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyNotBetween(String value1, String value2) {
            addCriterion("privateKey not between", value1, value2, "privatekey");
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