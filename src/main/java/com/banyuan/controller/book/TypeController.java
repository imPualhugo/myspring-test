package com.banyuan.controller.book;


import com.banyuan.bean.book.TypeBean;
import com.banyuan.message.ResponseData;
import com.banyuan.service.book.TypeService;
import com.banyuan.service.book.impl.TypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.banyuan.message.MessageData.success;

@RestController
@RequestMapping("/type")
public class TypeController {

    @Autowired
    private TypeService typeService;


    @DeleteMapping("/{id}")
    public ResponseData delete(@PathVariable Integer id) throws Exception {
        TypeBean bean = new TypeBean();
        bean.setId(id);
        typeService.deleteType(bean);
        return success();
    }

    @GetMapping("")
    public ResponseData getAll(TypeBean bean) throws Exception {
        return success(typeService.getAll());
    }


    @PostMapping("")
    public ResponseData insert(@RequestBody TypeBean bean) throws Exception {
        typeService.insertType(bean);
        return success();
    }

    @PutMapping ("")
    public ResponseData update(@RequestBody TypeBean bean) throws Exception {
        System.out.println(bean);
        typeService.updateType(bean);
        return success();
    }


    @GetMapping("/page/{pageNo}")
    public ResponseData get(@PathVariable Integer pageNo,String name) throws Exception {
        TypeBean bean = new TypeBean();
        bean.setPageNo(pageNo);
        bean.setName(name);
        return success(typeService.getList(bean));
    }


}
