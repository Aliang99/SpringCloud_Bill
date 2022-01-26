package com.aliang.controller;

import com.aliang.common.CommonVo;
import com.aliang.pojo.Bill;
import com.aliang.pojo.BillType;
import com.aliang.service.BillService;
import com.aliang.service.TypeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("bill")
public class BillController {

    @Resource
    private BillService billService;
    @Resource
    private TypeService typeService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("test")
    @ResponseBody
    public String test() {
        return "Hello SpringBoot...";
    }

    /**
     * 分页+条件查
     *
     * @param pageNum
     * @param pageSize
     * @return 状态： OK
     */
    @GetMapping("page")
    @ResponseBody
    public CommonVo getPage(@RequestParam(defaultValue = "1") int pageNum,
                            @RequestParam(defaultValue = "2") int pageSize,
                            Bill bill) {
        System.out.println("pageNum:" + pageNum);
        System.out.println("pageSize:" + pageSize);
        if (bill.getTypeId() != null && bill.getTypeId() <= 0) {
            bill.setTypeId(null);
        }
        List<BillType> typeList = typeService.list();
        Map<String, Object> map = new HashMap<>();
        map.put("types", typeList);
        PageInfo<Bill> pageInfo = billService.getPage(bill, pageNum, pageSize);
        map.put("page", pageInfo);
        map.put("bill", bill);
        CommonVo vo = new CommonVo(200, "请求成功");
        vo.setData(map);
        return vo;
    }


    /**
     * 添加账单信息
     *
     * @param bill
     * @return 状态： OK
     */
    @PutMapping("add")
    @ResponseBody
    public CommonVo add(Bill bill) {
        int add = billService.add(bill);
        System.out.println(add > 0 ? "OK" : "No");
        if (add > 0) {
            return new CommonVo(200, "添加成功", null);
        }
        return new CommonVo(500, "添加失败", null);
    }


    /**
     * 获取分类列表
     *
     * @return 状态：OK
     */
    @GetMapping("getTypes")
    @ResponseBody
    public CommonVo getTypes() {
        System.out.println("被访问了...........");
        //查询账单的全部类型
        List<BillType> typeList = typeService.list();
        return new CommonVo(200, "ok", typeList);
    }


    /**
     * 根据id查询
     *
     * @param id
     * @return 状态： NO
     */
    @GetMapping("/queryById/{id}")
    @ResponseBody
    public CommonVo updatePage(@PathVariable("id") Long id) {
        //根据id查询对应的数据
        Bill bill = billService.selectById(id);
        return new CommonVo(200, "OK", bill);
    }

    /**
     * 提交修改信息
     *
     * @param bill
     * @return 状态： NO
     */
    @PostMapping("update")
    @ResponseBody
    public CommonVo update(Bill bill) {
        System.out.println(bill);
        int update = billService.update(bill);
        if (update > 0) {
            return new CommonVo(200, "更新成功", null);
        }
        return new CommonVo(500, "更新失败", null);
    }

    /**
     * 根据id删除bill
     *
     * @param id
     * @return 状态： NO
     */
    @DeleteMapping("delete/{id}")
    @ResponseBody
    public CommonVo delete(@PathVariable("id") Long id) {
        int delete = billService.delete(id);
        if (delete > 0) {
            return new CommonVo(200, "删除成功", null);
        }
        return new CommonVo(500, "删除失败", null);
    }
}
