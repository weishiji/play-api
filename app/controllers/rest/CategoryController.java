package controllers.rest;


import com.avaje.ebean.Ebean;
import com.avaje.ebean.Transaction;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import models.*;
import play.libs.Json;
import play.mvc.Result;

import javax.inject.Inject;

/**
 * Created by lxg on 05/04/2017.
 */
public class CategoryController  extends Controller{

    private FormFactory formFactory;
    @Inject
    public CategoryController(FormFactory formFactory) {
        this.formFactory = formFactory;
    }

    public Result list(){
        Category category = new Category();
        return ok(
                //"hello World"
                Json.toJson(category.list())
        );

    }
    public Result create(){
        Category category = new Category();
        Form<Category> categoryForm = formFactory.form(Category.class).bindFromRequest();
        /**
         *   基本类型的错误，无法验证通过
         * */
        if(categoryForm.hasErrors()){
            return ok(
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
            category.save();
            /*Computer savedComputer = Computer.find.byId(id);
            if (savedComputer != null) {
                Computer newComputerData = computerForm.get();
                savedComputer.company = newComputerData.company;
                savedComputer.discontinued = newComputerData.discontinued;
                savedComputer.introduced = newComputerData.introduced;
                savedComputer.name = newComputerData.name;

                savedComputer.update();
                flash("success", "Computer " + computerForm.get().name + " has been updated");
                txn.commit();
            }*/
        } finally {
            txn.end();
        }



        return ok(
                //Json.toJson()
                "success"
        );
    }
}
