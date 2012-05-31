///**
// * Global Comments Here                                                                                  
// */
//package com.hp.common.web;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.struts2.ServletActionContext;
//
//import com.hp.common.entity.AbstractEntity;
//import com.hp.common.service.BaseService;
//import com.hp.common.util.ReflectExtUtil;
//import com.opensymphony.xwork2.ModelDriven;
//import com.opensymphony.xwork2.Preparable;
//
///**
// *Purpose   : 
// *Class Name: BaseActionSupport.java    
// *Package   : com.hp.common.web                                                                   
// *@version $Id: BaseActionSupport.java 21 2008-12-28 03:20:37Z lixia $                                                          
// *@author xiali2
// *@since 2008-12-14 
// */
//@SuppressWarnings("serial")
//public class BaseActionSupport<T extends AbstractEntity> extends
//        SimpleActionSupport implements Preparable, ModelDriven<T> {
//
//    protected static final String INDEX = "index";
//
//    private BaseService<T> baseService;
//
//    /**
//     * @param baseService the baseService to set
//     */
//    @SuppressWarnings("unchecked")
//    public void setBaseService(BaseService<T> baseService) {
//        this.baseService = baseService;
//        Class<T> clazz = ReflectExtUtil.getGenericClass(this.getClass());
//        if (clazz != null) {
//            baseService.setEntityClass(clazz);
//        }
//        logger.debug("baseService=" + baseService);
//    }
//
//    protected BaseService<T> getBaseService() {
//        return baseService;
//    }
//
//    // the model
//    protected T entity;
//
//    // model's Class
//    protected Class<T> entityClass;
//
//    public String index() {
//        return "index";
//    }
//
//    public String input() {
//        return "input";
//    }
//
//    /**
//     * Action函数,默认action函数，默认指向list函数.
//     */
//    @Override
//    public String execute() throws Exception {
//        return index();
//    }
//
//    /**
//     * 在save()前执行二次绑宄1�7.
//     */
//    public void prepareSave() throws Exception {
//        prepareModel();
//    }
//
//    /**
//     * 在input()前执行二次绑宄1�7.
//     */
//    public void prepareInput() throws Exception {
//        prepareModel();
//    }
//
//    /**
//     * 屏蔽公共的二次绑宄1�7.
//     */
//    public void prepare() throws Exception {
//    }
//
//    /**
//     * 从参数中取得主键参数倄1�7,可在子类重载
//     */
//    protected Long getSid() {
//        HttpServletRequest request = ServletActionContext.getRequest();
//        String sid = request.getParameter("sid");
//        if (StringUtils.isNotEmpty(sid)) {
//            return new Long(sid);
//        } else {
//            return null;
//        }
//    }
//
//    /**
//     * 等同于prepare()的内部函敄1�7. 
//     */
//    protected void prepareModel() {
//        if (entityClass == null || getBaseService() == null) {
//            return;
//        }
//        if (getSid() != null) {
//            entity = getBaseService().findByPrimaryKey(getSid());
//            if (entity == null)
//                throw new IllegalArgumentException("id not exist");
//        } else {
//            try {
//                entity = entityClass.newInstance();
//            } catch (InstantiationException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /* (non-Javadoc)
//     * @see com.opensymphony.xwork2.ModelDriven#getModel()
//     */
//    public T getModel() {
//        return entity;
//    }
//
//    public HttpServletRequest getRequest() {
//        HttpServletRequest request = ServletActionContext.getRequest();
//        return request;
//    }
//
//    public HttpServletResponse getResponse() {
//        HttpServletResponse response = ServletActionContext.getResponse();
//        return response;
//    }
//}
