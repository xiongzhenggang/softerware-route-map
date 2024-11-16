package com.xzg.performance.computing.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xzg.performance.computing.model.MapData;
import com.xzg.performance.computing.model.RouteRequest;
import com.xzg.performance.computing.model.RouteResult;
import com.xzg.performance.computing.service.NavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @projectName: high-performance-computing-demo
 * @package: com.xzg.performance.computing.api
 * @className: NavigationController
 * @author: xzg
 * @description: TODO
 * @date: 16/11/2024-下午 5:13
 * @version: 1.0
 */
@Controller
public class NavigationController {

    @Autowired
    private NavigationService navigationService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/upload")
    @ResponseBody
    public String uploadMap(@RequestParam("file") MultipartFile file) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            MapData mapData = mapper.readValue(file.getBytes(), MapData.class);
            navigationService.loadMapData(mapData);
            return "地图数据加载成功";
        } catch (Exception e) {
            return "地图数据加载失败: " + e.getMessage();
        }
    }

    @PostMapping("/route")
    @ResponseBody
    public RouteResult calculateRoute(@RequestBody RouteRequest request) {
        return navigationService.calculateRoute(
                request.getStartPoint(),
                request.getEndPoint()
        );
    }
}
    