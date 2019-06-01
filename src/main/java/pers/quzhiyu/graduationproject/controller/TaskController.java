package pers.quzhiyu.graduationproject.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.quzhiyu.graduationproject.domain.Task;
import pers.quzhiyu.graduationproject.dto.TaskInfo;
import pers.quzhiyu.graduationproject.service.TaskService;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

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
        dataHandler(tasks, arrays, sdf);
        return arrays;
    }

    @GetMapping("/date/staff/{year}/{month}/{id}")
    public List<List<String>> findStaffVisualDataByDate(@PathVariable Long year, @PathVariable Long month, @PathVariable Long id) {
        String begin = year + "-" + month + "-1";
        String end = month == 12 ? (year + 1) + "-1-1" : year + "-" + (month + 1) +"-1";
        System.out.println(begin);
        System.out.println(end);

        List<Task> tasks = taskService.findStaffVisualDataByDate(begin,end, id);
        List<List<String>> arrays = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        dataHandler(tasks, arrays, sdf);
        return arrays;
    }

    private void dataHandler(List<Task> tasks, List<List<String>> arrays, SimpleDateFormat sdf) {
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
        Timestamp endTime = task.getEndTime();
        Timestamp time = new Timestamp(new Date().getTime());
        // 当修改后的结束时间早于当前时间，就会变为超时状态。
        if(time.after(endTime)) {
            task.setStage(6L);
        }
        // 当修改后的结束时间晚于当前时间，并且状态为超时
        if(time.before(endTime) && task.getStage() == 6L) {
            // 如果是开发任务，就将状态变为开发中
            if(task.getIsTest() == 0)
                task.setStage(2L);
            // 如果是测试任务，就将状态变为测试中
            if(task.getIsTest() == 1)
                task.setStage(3L);
        }
        return taskService.updateTask(task);
    }

    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("删除任务服务")
    public int deleteTask(@PathVariable Long id){
        //删除开发任务也需要删除对应的测试任务
        return taskService.deleteTaskById(id);
    }

    @DeleteMapping("/test/{testId:\\d+}/{taskId:\\d+}")
    @ApiOperation("删除测试任务服务")
    public int deleteTestTask(@PathVariable Long testId, @PathVariable Long taskId) {
        // 因为一个开发任务只能让一个测试人员测试
        // 因此删除测试任务需要把测试中的开发任务变为待分配
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
