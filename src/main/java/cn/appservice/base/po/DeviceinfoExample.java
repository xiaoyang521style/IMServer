package cn.appservice.base.po;

import java.util.ArrayList;
import java.util.List;

public class DeviceinfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DeviceinfoExample() {
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

        public Criteria andDevicemodelIsNull() {
            addCriterion("deviceModel is null");
            return (Criteria) this;
        }

        public Criteria andDevicemodelIsNotNull() {
            addCriterion("deviceModel is not null");
            return (Criteria) this;
        }

        public Criteria andDevicemodelEqualTo(Integer value) {
            addCriterion("deviceModel =", value, "devicemodel");
            return (Criteria) this;
        }

        public Criteria andDevicemodelNotEqualTo(Integer value) {
            addCriterion("deviceModel <>", value, "devicemodel");
            return (Criteria) this;
        }

        public Criteria andDevicemodelGreaterThan(Integer value) {
            addCriterion("deviceModel >", value, "devicemodel");
            return (Criteria) this;
        }

        public Criteria andDevicemodelGreaterThanOrEqualTo(Integer value) {
            addCriterion("deviceModel >=", value, "devicemodel");
            return (Criteria) this;
        }

        public Criteria andDevicemodelLessThan(Integer value) {
            addCriterion("deviceModel <", value, "devicemodel");
            return (Criteria) this;
        }

        public Criteria andDevicemodelLessThanOrEqualTo(Integer value) {
            addCriterion("deviceModel <=", value, "devicemodel");
            return (Criteria) this;
        }

        public Criteria andDevicemodelIn(List<Integer> values) {
            addCriterion("deviceModel in", values, "devicemodel");
            return (Criteria) this;
        }

        public Criteria andDevicemodelNotIn(List<Integer> values) {
            addCriterion("deviceModel not in", values, "devicemodel");
            return (Criteria) this;
        }

        public Criteria andDevicemodelBetween(Integer value1, Integer value2) {
            addCriterion("deviceModel between", value1, value2, "devicemodel");
            return (Criteria) this;
        }

        public Criteria andDevicemodelNotBetween(Integer value1, Integer value2) {
            addCriterion("deviceModel not between", value1, value2, "devicemodel");
            return (Criteria) this;
        }

        public Criteria andDevicenameIsNull() {
            addCriterion("deviceName is null");
            return (Criteria) this;
        }

        public Criteria andDevicenameIsNotNull() {
            addCriterion("deviceName is not null");
            return (Criteria) this;
        }

        public Criteria andDevicenameEqualTo(String value) {
            addCriterion("deviceName =", value, "devicename");
            return (Criteria) this;
        }

        public Criteria andDevicenameNotEqualTo(String value) {
            addCriterion("deviceName <>", value, "devicename");
            return (Criteria) this;
        }

        public Criteria andDevicenameGreaterThan(String value) {
            addCriterion("deviceName >", value, "devicename");
            return (Criteria) this;
        }

        public Criteria andDevicenameGreaterThanOrEqualTo(String value) {
            addCriterion("deviceName >=", value, "devicename");
            return (Criteria) this;
        }

        public Criteria andDevicenameLessThan(String value) {
            addCriterion("deviceName <", value, "devicename");
            return (Criteria) this;
        }

        public Criteria andDevicenameLessThanOrEqualTo(String value) {
            addCriterion("deviceName <=", value, "devicename");
            return (Criteria) this;
        }

        public Criteria andDevicenameLike(String value) {
            addCriterion("deviceName like", value, "devicename");
            return (Criteria) this;
        }

        public Criteria andDevicenameNotLike(String value) {
            addCriterion("deviceName not like", value, "devicename");
            return (Criteria) this;
        }

        public Criteria andDevicenameIn(List<String> values) {
            addCriterion("deviceName in", values, "devicename");
            return (Criteria) this;
        }

        public Criteria andDevicenameNotIn(List<String> values) {
            addCriterion("deviceName not in", values, "devicename");
            return (Criteria) this;
        }

        public Criteria andDevicenameBetween(String value1, String value2) {
            addCriterion("deviceName between", value1, value2, "devicename");
            return (Criteria) this;
        }

        public Criteria andDevicenameNotBetween(String value1, String value2) {
            addCriterion("deviceName not between", value1, value2, "devicename");
            return (Criteria) this;
        }

        public Criteria andSystemversionIsNull() {
            addCriterion("systemVersion is null");
            return (Criteria) this;
        }

        public Criteria andSystemversionIsNotNull() {
            addCriterion("systemVersion is not null");
            return (Criteria) this;
        }

        public Criteria andSystemversionEqualTo(String value) {
            addCriterion("systemVersion =", value, "systemversion");
            return (Criteria) this;
        }

        public Criteria andSystemversionNotEqualTo(String value) {
            addCriterion("systemVersion <>", value, "systemversion");
            return (Criteria) this;
        }

        public Criteria andSystemversionGreaterThan(String value) {
            addCriterion("systemVersion >", value, "systemversion");
            return (Criteria) this;
        }

        public Criteria andSystemversionGreaterThanOrEqualTo(String value) {
            addCriterion("systemVersion >=", value, "systemversion");
            return (Criteria) this;
        }

        public Criteria andSystemversionLessThan(String value) {
            addCriterion("systemVersion <", value, "systemversion");
            return (Criteria) this;
        }

        public Criteria andSystemversionLessThanOrEqualTo(String value) {
            addCriterion("systemVersion <=", value, "systemversion");
            return (Criteria) this;
        }

        public Criteria andSystemversionLike(String value) {
            addCriterion("systemVersion like", value, "systemversion");
            return (Criteria) this;
        }

        public Criteria andSystemversionNotLike(String value) {
            addCriterion("systemVersion not like", value, "systemversion");
            return (Criteria) this;
        }

        public Criteria andSystemversionIn(List<String> values) {
            addCriterion("systemVersion in", values, "systemversion");
            return (Criteria) this;
        }

        public Criteria andSystemversionNotIn(List<String> values) {
            addCriterion("systemVersion not in", values, "systemversion");
            return (Criteria) this;
        }

        public Criteria andSystemversionBetween(String value1, String value2) {
            addCriterion("systemVersion between", value1, value2, "systemversion");
            return (Criteria) this;
        }

        public Criteria andSystemversionNotBetween(String value1, String value2) {
            addCriterion("systemVersion not between", value1, value2, "systemversion");
            return (Criteria) this;
        }

        public Criteria andDevicetokenIsNull() {
            addCriterion("deviceToken is null");
            return (Criteria) this;
        }

        public Criteria andDevicetokenIsNotNull() {
            addCriterion("deviceToken is not null");
            return (Criteria) this;
        }

        public Criteria andDevicetokenEqualTo(String value) {
            addCriterion("deviceToken =", value, "devicetoken");
            return (Criteria) this;
        }

        public Criteria andDevicetokenNotEqualTo(String value) {
            addCriterion("deviceToken <>", value, "devicetoken");
            return (Criteria) this;
        }

        public Criteria andDevicetokenGreaterThan(String value) {
            addCriterion("deviceToken >", value, "devicetoken");
            return (Criteria) this;
        }

        public Criteria andDevicetokenGreaterThanOrEqualTo(String value) {
            addCriterion("deviceToken >=", value, "devicetoken");
            return (Criteria) this;
        }

        public Criteria andDevicetokenLessThan(String value) {
            addCriterion("deviceToken <", value, "devicetoken");
            return (Criteria) this;
        }

        public Criteria andDevicetokenLessThanOrEqualTo(String value) {
            addCriterion("deviceToken <=", value, "devicetoken");
            return (Criteria) this;
        }

        public Criteria andDevicetokenLike(String value) {
            addCriterion("deviceToken like", value, "devicetoken");
            return (Criteria) this;
        }

        public Criteria andDevicetokenNotLike(String value) {
            addCriterion("deviceToken not like", value, "devicetoken");
            return (Criteria) this;
        }

        public Criteria andDevicetokenIn(List<String> values) {
            addCriterion("deviceToken in", values, "devicetoken");
            return (Criteria) this;
        }

        public Criteria andDevicetokenNotIn(List<String> values) {
            addCriterion("deviceToken not in", values, "devicetoken");
            return (Criteria) this;
        }

        public Criteria andDevicetokenBetween(String value1, String value2) {
            addCriterion("deviceToken between", value1, value2, "devicetoken");
            return (Criteria) this;
        }

        public Criteria andDevicetokenNotBetween(String value1, String value2) {
            addCriterion("deviceToken not between", value1, value2, "devicetoken");
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