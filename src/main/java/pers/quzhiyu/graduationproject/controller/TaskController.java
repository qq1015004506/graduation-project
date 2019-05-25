package pers.quzhiyu.graduationproject.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.quzhiyu.graduationproject.domain.Staff;
import pers.quzhiyu.graduationproject.domain.Task;
import pers.quzhiyu.graduationproject.dto.TaskInfo;
import pers.quzhiyu.graduationproject.service.TaskService;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/task")
@CrossOrigin
public class TaskController {
    @Autowired
    TaskService taskService;

    @GetMapping("/{id:\\d+}")
    @ApiOperation("通过ID查询任务信息服务")
    public Task findTaskById(@PathVariable Long id) {
        return taskService.findTaskById(id);
    }

    @GetMapping("/date/{year}/{month}")
    public List<List<String>> findVisualDataByDate(@PathVariable Long year, @PathVariable Long month) {
        String begin = year + "-" + month + "-1";
        String end = month == 12 ? (year + 1) + "-1-1" : year + "-" + (month + 1) +"-1";
        System.out.println(begin);
        System.out.println(end);

        List<Task> tasks = taskService.findVisualDataByDate(begin,end);
        List<List<String>> arrays = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (Task task : tasks) {
            List<String> strs = new ArrayList<>();
            strs.add(sdf.format(task.getStartTime()));
            strs.add(task.getName());
            strs.add("开始");
            strs.add(task.getQuantity().toString());
            strs.add(task.getStage().toString());
            arrays.add(strs);
            strs = new ArrayList<>();
            strs.add(sdf.format(task.getEndTime()));
            strs.add(task.getName());
            strs.add("结束");
            strs.add(task.getQuantity().toString());
            strs.add(task.getStage().toString());
            arrays.add(strs);
        }
        return arrays;
    }
    @GetMapping("/group/{id:\\d+}")
    @ApiOperation("为测试人员提供的通过分组查询任务信息服务")
    public List<Task> findTaskByGroupForTester(@PathVariable Long id) {
        return taskService.findTaskByGroupForTester(id);
    }

    @GetMapping("/staff/{id:\\d+}")
    @ApiOperation("通过员工ID查询任务服务")
    public List<Task> findTaskByStaffId(@PathVariable Long id) {
        return taskService.findTaskByStaffId(id);
    }
    @GetMapping("/query")
    @ApiOperation("查询任务服务")
    public Map<String,Object> queryTask(@RequestParam(value = "name",defaultValue = "")String name,
                                        @RequestParam(value = "staffName",defaultValue = "")String staffName,Long group,
                                        @RequestParam(value = "current",defaultValue = "1")int current,
                                        @RequestParam(value = "size",defaultValue = "10")int size) {
        PageHelper.startPage(current,size);
        List<TaskInfo> staff = taskService.findAllTask(name, staffName,group);
        PageInfo<TaskInfo> pageInfo = new PageInfo<>(staff);
        Map<String,Object> data = new HashMap<>();
        data.put("total",pageInfo.getTotal());//总条数
        data.put("current",current);//当前页
        data.put("data",pageInfo.getList());//数据
        return data;
    }

    @GetMapping()
    @ApiOperation("获取全部任务信息服务")
    public List<TaskInfo> findAllTask() {
        return taskService.findAllTask();
    }

    @PostMapping()
    @ApiOperation("创建开发任务服务")
    public int createTask(@RequestBody Task task) {
        return taskService.insertTask(task);
    }

    @PostMapping("/test")
    @ApiOperation("创建测试任务服务")
    public int createTestTask(@RequestBody Task task) {
        return taskService.createTestTask(task);
    }

    @PutMapping()
    @ApiOperation("更新任务服务")
    public int UpdateTask(@RequestBody Task task) {
        return taskService.updateTask(task);
    }

    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("删除任务服务")
    public int deleteTask(@PathVariable Long id){
        return taskService.deleteTaskById(id);
    }

    @DeleteMapping("/test/{testId:\\d+}/{taskId:\\d+}")
    @ApiOperation("删除测试任务服务")
    public int deleteTestTask(@PathVariable Long testId, @PathVariable Long taskId) {
        return taskService.deleteTestTask(testId,taskId);
    }

    @PutMapping("/{taskId:\\d+}/codeId/{codeId:\\d+}")
    @ApiOperation("更新代码版本服务")
    public int changeTaskCodeInfo(@PathVariable Long taskId,@PathVariable Long codeId) {
        return taskService.changeTaskCodeInfo(taskId,codeId);
    }

    @PutMapping("/eval")
    @ApiOperation("添加测试评论服务")
    public int testResult(@RequestBody Task task) {
        System.out.println(task);
        return taskService.testResult(task);
    }


}
