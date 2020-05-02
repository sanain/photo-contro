package com.sanain.photo.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PtAlbumTempExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PtAlbumTempExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andCoverPathsIsNull() {
            addCriterion("cover_paths is null");
            return (Criteria) this;
        }

        public Criteria andCoverPathsIsNotNull() {
            addCriterion("cover_paths is not null");
            return (Criteria) this;
        }

        public Criteria andCoverPathsEqualTo(String value) {
            addCriterion("cover_paths =", value, "coverPaths");
            return (Criteria) this;
        }

        public Criteria andCoverPathsNotEqualTo(String value) {
            addCriterion("cover_paths <>", value, "coverPaths");
            return (Criteria) this;
        }

        public Criteria andCoverPathsGreaterThan(String value) {
            addCriterion("cover_paths >", value, "coverPaths");
            return (Criteria) this;
        }

        public Criteria andCoverPathsGreaterThanOrEqualTo(String value) {
            addCriterion("cover_paths >=", value, "coverPaths");
            return (Criteria) this;
        }

        public Criteria andCoverPathsLessThan(String value) {
            addCriterion("cover_paths <", value, "coverPaths");
            return (Criteria) this;
        }

        public Criteria andCoverPathsLessThanOrEqualTo(String value) {
            addCriterion("cover_paths <=", value, "coverPaths");
            return (Criteria) this;
        }

        public Criteria andCoverPathsLike(String value) {
            addCriterion("cover_paths like", value, "coverPaths");
            return (Criteria) this;
        }

        public Criteria andCoverPathsNotLike(String value) {
            addCriterion("cover_paths not like", value, "coverPaths");
            return (Criteria) this;
        }

        public Criteria andCoverPathsIn(List<String> values) {
            addCriterion("cover_paths in", values, "coverPaths");
            return (Criteria) this;
        }

        public Criteria andCoverPathsNotIn(List<String> values) {
            addCriterion("cover_paths not in", values, "coverPaths");
            return (Criteria) this;
        }

        public Criteria andCoverPathsBetween(String value1, String value2) {
            addCriterion("cover_paths between", value1, value2, "coverPaths");
            return (Criteria) this;
        }

        public Criteria andCoverPathsNotBetween(String value1, String value2) {
            addCriterion("cover_paths not between", value1, value2, "coverPaths");
            return (Criteria) this;
        }

        public Criteria andPhotoPathsIsNull() {
            addCriterion("photo_paths is null");
            return (Criteria) this;
        }

        public Criteria andPhotoPathsIsNotNull() {
            addCriterion("photo_paths is not null");
            return (Criteria) this;
        }

        public Criteria andPhotoPathsEqualTo(String value) {
            addCriterion("photo_paths =", value, "photoPaths");
            return (Criteria) this;
        }

        public Criteria andPhotoPathsNotEqualTo(String value) {
            addCriterion("photo_paths <>", value, "photoPaths");
            return (Criteria) this;
        }

        public Criteria andPhotoPathsGreaterThan(String value) {
            addCriterion("photo_paths >", value, "photoPaths");
            return (Criteria) this;
        }

        public Criteria andPhotoPathsGreaterThanOrEqualTo(String value) {
            addCriterion("photo_paths >=", value, "photoPaths");
            return (Criteria) this;
        }

        public Criteria andPhotoPathsLessThan(String value) {
            addCriterion("photo_paths <", value, "photoPaths");
            return (Criteria) this;
        }

        public Criteria andPhotoPathsLessThanOrEqualTo(String value) {
            addCriterion("photo_paths <=", value, "photoPaths");
            return (Criteria) this;
        }

        public Criteria andPhotoPathsLike(String value) {
            addCriterion("photo_paths like", value, "photoPaths");
            return (Criteria) this;
        }

        public Criteria andPhotoPathsNotLike(String value) {
            addCriterion("photo_paths not like", value, "photoPaths");
            return (Criteria) this;
        }

        public Criteria andPhotoPathsIn(List<String> values) {
            addCriterion("photo_paths in", values, "photoPaths");
            return (Criteria) this;
        }

        public Criteria andPhotoPathsNotIn(List<String> values) {
            addCriterion("photo_paths not in", values, "photoPaths");
            return (Criteria) this;
        }

        public Criteria andPhotoPathsBetween(String value1, String value2) {
            addCriterion("photo_paths between", value1, value2, "photoPaths");
            return (Criteria) this;
        }

        public Criteria andPhotoPathsNotBetween(String value1, String value2) {
            addCriterion("photo_paths not between", value1, value2, "photoPaths");
            return (Criteria) this;
        }

        public Criteria andJsPathsIsNull() {
            addCriterion("js_paths is null");
            return (Criteria) this;
        }

        public Criteria andJsPathsIsNotNull() {
            addCriterion("js_paths is not null");
            return (Criteria) this;
        }

        public Criteria andJsPathsEqualTo(String value) {
            addCriterion("js_paths =", value, "jsPaths");
            return (Criteria) this;
        }

        public Criteria andJsPathsNotEqualTo(String value) {
            addCriterion("js_paths <>", value, "jsPaths");
            return (Criteria) this;
        }

        public Criteria andJsPathsGreaterThan(String value) {
            addCriterion("js_paths >", value, "jsPaths");
            return (Criteria) this;
        }

        public Criteria andJsPathsGreaterThanOrEqualTo(String value) {
            addCriterion("js_paths >=", value, "jsPaths");
            return (Criteria) this;
        }

        public Criteria andJsPathsLessThan(String value) {
            addCriterion("js_paths <", value, "jsPaths");
            return (Criteria) this;
        }

        public Criteria andJsPathsLessThanOrEqualTo(String value) {
            addCriterion("js_paths <=", value, "jsPaths");
            return (Criteria) this;
        }

        public Criteria andJsPathsLike(String value) {
            addCriterion("js_paths like", value, "jsPaths");
            return (Criteria) this;
        }

        public Criteria andJsPathsNotLike(String value) {
            addCriterion("js_paths not like", value, "jsPaths");
            return (Criteria) this;
        }

        public Criteria andJsPathsIn(List<String> values) {
            addCriterion("js_paths in", values, "jsPaths");
            return (Criteria) this;
        }

        public Criteria andJsPathsNotIn(List<String> values) {
            addCriterion("js_paths not in", values, "jsPaths");
            return (Criteria) this;
        }

        public Criteria andJsPathsBetween(String value1, String value2) {
            addCriterion("js_paths between", value1, value2, "jsPaths");
            return (Criteria) this;
        }

        public Criteria andJsPathsNotBetween(String value1, String value2) {
            addCriterion("js_paths not between", value1, value2, "jsPaths");
            return (Criteria) this;
        }

        public Criteria andCssPathsIsNull() {
            addCriterion("css_paths is null");
            return (Criteria) this;
        }

        public Criteria andCssPathsIsNotNull() {
            addCriterion("css_paths is not null");
            return (Criteria) this;
        }

        public Criteria andCssPathsEqualTo(String value) {
            addCriterion("css_paths =", value, "cssPaths");
            return (Criteria) this;
        }

        public Criteria andCssPathsNotEqualTo(String value) {
            addCriterion("css_paths <>", value, "cssPaths");
            return (Criteria) this;
        }

        public Criteria andCssPathsGreaterThan(String value) {
            addCriterion("css_paths >", value, "cssPaths");
            return (Criteria) this;
        }

        public Criteria andCssPathsGreaterThanOrEqualTo(String value) {
            addCriterion("css_paths >=", value, "cssPaths");
            return (Criteria) this;
        }

        public Criteria andCssPathsLessThan(String value) {
            addCriterion("css_paths <", value, "cssPaths");
            return (Criteria) this;
        }

        public Criteria andCssPathsLessThanOrEqualTo(String value) {
            addCriterion("css_paths <=", value, "cssPaths");
            return (Criteria) this;
        }

        public Criteria andCssPathsLike(String value) {
            addCriterion("css_paths like", value, "cssPaths");
            return (Criteria) this;
        }

        public Criteria andCssPathsNotLike(String value) {
            addCriterion("css_paths not like", value, "cssPaths");
            return (Criteria) this;
        }

        public Criteria andCssPathsIn(List<String> values) {
            addCriterion("css_paths in", values, "cssPaths");
            return (Criteria) this;
        }

        public Criteria andCssPathsNotIn(List<String> values) {
            addCriterion("css_paths not in", values, "cssPaths");
            return (Criteria) this;
        }

        public Criteria andCssPathsBetween(String value1, String value2) {
            addCriterion("css_paths between", value1, value2, "cssPaths");
            return (Criteria) this;
        }

        public Criteria andCssPathsNotBetween(String value1, String value2) {
            addCriterion("css_paths not between", value1, value2, "cssPaths");
            return (Criteria) this;
        }

        public Criteria andHtmlStrIsNull() {
            addCriterion("html_str is null");
            return (Criteria) this;
        }

        public Criteria andHtmlStrIsNotNull() {
            addCriterion("html_str is not null");
            return (Criteria) this;
        }

        public Criteria andHtmlStrEqualTo(String value) {
            addCriterion("html_str =", value, "htmlStr");
            return (Criteria) this;
        }

        public Criteria andHtmlStrNotEqualTo(String value) {
            addCriterion("html_str <>", value, "htmlStr");
            return (Criteria) this;
        }

        public Criteria andHtmlStrGreaterThan(String value) {
            addCriterion("html_str >", value, "htmlStr");
            return (Criteria) this;
        }

        public Criteria andHtmlStrGreaterThanOrEqualTo(String value) {
            addCriterion("html_str >=", value, "htmlStr");
            return (Criteria) this;
        }

        public Criteria andHtmlStrLessThan(String value) {
            addCriterion("html_str <", value, "htmlStr");
            return (Criteria) this;
        }

        public Criteria andHtmlStrLessThanOrEqualTo(String value) {
            addCriterion("html_str <=", value, "htmlStr");
            return (Criteria) this;
        }

        public Criteria andHtmlStrLike(String value) {
            addCriterion("html_str like", value, "htmlStr");
            return (Criteria) this;
        }

        public Criteria andHtmlStrNotLike(String value) {
            addCriterion("html_str not like", value, "htmlStr");
            return (Criteria) this;
        }

        public Criteria andHtmlStrIn(List<String> values) {
            addCriterion("html_str in", values, "htmlStr");
            return (Criteria) this;
        }

        public Criteria andHtmlStrNotIn(List<String> values) {
            addCriterion("html_str not in", values, "htmlStr");
            return (Criteria) this;
        }

        public Criteria andHtmlStrBetween(String value1, String value2) {
            addCriterion("html_str between", value1, value2, "htmlStr");
            return (Criteria) this;
        }

        public Criteria andHtmlStrNotBetween(String value1, String value2) {
            addCriterion("html_str not between", value1, value2, "htmlStr");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andPhotoCountIsNull() {
            addCriterion("photo_count is null");
            return (Criteria) this;
        }

        public Criteria andPhotoCountIsNotNull() {
            addCriterion("photo_count is not null");
            return (Criteria) this;
        }

        public Criteria andPhotoCountEqualTo(Integer value) {
            addCriterion("photo_count =", value, "photoCount");
            return (Criteria) this;
        }

        public Criteria andPhotoCountNotEqualTo(Integer value) {
            addCriterion("photo_count <>", value, "photoCount");
            return (Criteria) this;
        }

        public Criteria andPhotoCountGreaterThan(Integer value) {
            addCriterion("photo_count >", value, "photoCount");
            return (Criteria) this;
        }

        public Criteria andPhotoCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("photo_count >=", value, "photoCount");
            return (Criteria) this;
        }

        public Criteria andPhotoCountLessThan(Integer value) {
            addCriterion("photo_count <", value, "photoCount");
            return (Criteria) this;
        }

        public Criteria andPhotoCountLessThanOrEqualTo(Integer value) {
            addCriterion("photo_count <=", value, "photoCount");
            return (Criteria) this;
        }

        public Criteria andPhotoCountIn(List<Integer> values) {
            addCriterion("photo_count in", values, "photoCount");
            return (Criteria) this;
        }

        public Criteria andPhotoCountNotIn(List<Integer> values) {
            addCriterion("photo_count not in", values, "photoCount");
            return (Criteria) this;
        }

        public Criteria andPhotoCountBetween(Integer value1, Integer value2) {
            addCriterion("photo_count between", value1, value2, "photoCount");
            return (Criteria) this;
        }

        public Criteria andPhotoCountNotBetween(Integer value1, Integer value2) {
            addCriterion("photo_count not between", value1, value2, "photoCount");
            return (Criteria) this;
        }

        public Criteria andTempRemarkIsNull() {
            addCriterion("temp_remark is null");
            return (Criteria) this;
        }

        public Criteria andTempRemarkIsNotNull() {
            addCriterion("temp_remark is not null");
            return (Criteria) this;
        }

        public Criteria andTempRemarkEqualTo(String value) {
            addCriterion("temp_remark =", value, "tempRemark");
            return (Criteria) this;
        }

        public Criteria andTempRemarkNotEqualTo(String value) {
            addCriterion("temp_remark <>", value, "tempRemark");
            return (Criteria) this;
        }

        public Criteria andTempRemarkGreaterThan(String value) {
            addCriterion("temp_remark >", value, "tempRemark");
            return (Criteria) this;
        }

        public Criteria andTempRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("temp_remark >=", value, "tempRemark");
            return (Criteria) this;
        }

        public Criteria andTempRemarkLessThan(String value) {
            addCriterion("temp_remark <", value, "tempRemark");
            return (Criteria) this;
        }

        public Criteria andTempRemarkLessThanOrEqualTo(String value) {
            addCriterion("temp_remark <=", value, "tempRemark");
            return (Criteria) this;
        }

        public Criteria andTempRemarkLike(String value) {
            addCriterion("temp_remark like", value, "tempRemark");
            return (Criteria) this;
        }

        public Criteria andTempRemarkNotLike(String value) {
            addCriterion("temp_remark not like", value, "tempRemark");
            return (Criteria) this;
        }

        public Criteria andTempRemarkIn(List<String> values) {
            addCriterion("temp_remark in", values, "tempRemark");
            return (Criteria) this;
        }

        public Criteria andTempRemarkNotIn(List<String> values) {
            addCriterion("temp_remark not in", values, "tempRemark");
            return (Criteria) this;
        }

        public Criteria andTempRemarkBetween(String value1, String value2) {
            addCriterion("temp_remark between", value1, value2, "tempRemark");
            return (Criteria) this;
        }

        public Criteria andTempRemarkNotBetween(String value1, String value2) {
            addCriterion("temp_remark not between", value1, value2, "tempRemark");
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