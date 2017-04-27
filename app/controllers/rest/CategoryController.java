package controllers.rest;


import com.avaje.ebean.Ebean;
import com.avaje.ebean.Transaction;
import com.fasterxml.jackson.databind.JsonNode;
import io.netty.handler.codec.http.HttpRequest;
import play.Logger;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import models.*;
import play.libs.Json;
import play.mvc.Result;
import utils.ResponseJson;

import javax.inject.Inject;

import java.util.Date;
import java.util.List;

import static javax.security.auth.callback.ConfirmationCallback.OK;

/**
 * Created by lxg on 05/04/2017.
 */
public class CategoryController  extends Controller{

    private FormFactory formFactory;

    @Inject
    public CategoryController(FormFactory formFactory) {
        this.formFactory = formFactory;
    }
    /**
     * @apiNote
     * @return List
     * */
    public Result list(){

        List<Category> categories = Category.list();
        return ok(ResponseJson.format(categories,OK));
    }
    /**
     * @apiNote 创建分类
     * @return success
     * @return create object
     * */
    public Result create(){
        Category category = new Category();
        Form<Category> categoryForm = formFactory.form(Category.class).bindFromRequest();
        if(categoryForm.hasErrors()){
            return badRequest(
                    categoryForm.errorsAsJson()
            );
        }

        //DynamicForm requestData = formFactory.form().bindFromRequest();
        //Long category_id = Long.parseLong(requestData.get("category_id"));

        //category.setCategory_id(category_id);
        //category.save();

        Transaction txn = Ebean.beginTransaction();
        try {
            Category newCategoryData = categoryForm.get();

            category.setCategory_id(newCategoryData.getCategory_id());
            category.setName(newCategoryData.getName());
            category.setStatus(newCategoryData.getStatus());
            category.save();
            //TODO:又一坑，需要ebean server 提交保存
            txn.commit();
        }catch (Exception e){
            return internalServerError(ResponseJson.format(INTERNAL_SERVER_ERROR));
        }finally {
            txn.end(); //关闭连接
        }
        return created(ResponseJson.format(category,CREATED));
    }
    /**
     * @apiNote 删除分类
     * @param category_id of the category for delete
     * */
    public Result delete(Long category_id){

        Category.find.ref(category_id).delete();
        return ok(ResponseJson.format(OK));
    }
    /**
     * @apiNote 更新分类
     * @param category_id  of the category to edit
     * */
    public Result update(Long category_id){
        Form<Category> categoryForm = formFactory.form(Category.class).bindFromRequest();
        if(categoryForm.hasErrors()){
            return badRequest(
                    categoryForm.errorsAsJson()
            );
        }

        Transaction txn = Ebean.beginTransaction();
        try {

            //Category savedCategory = Category.find.byId(category_id);
            Category savedCategory = Category.getCategoryById(category_id);
            if(savedCategory != null){
                Category newCategoryData = categoryForm.get();
                savedCategory.setStatus(newCategoryData.getStatus());
                savedCategory.setName(newCategoryData.getName());

                savedCategory.update();
                txn.commit();
            }
            return ok(ResponseJson.format(savedCategory,OK));
        }catch (Exception e){
            e.printStackTrace();
            return internalServerError(ResponseJson.format(INTERNAL_SERVER_ERROR));
        }finally {
            txn.end();
        }
    }

}
