package com.xzg.performance.computing.model;

import lombok.Data;

import java.util.List;

/**
 * @projectName: high-performance-computing-demo
 * @package: com.xzg.performance.computing.model
 * @className: RouteResult
 * @author: xzg
 * @description: TODO
 * @date: 16/11/2024-下午 5:11
 * @version: 1.0
 */
@Data
public class RouteResult {
    private List<String> path;
    private double totalDistance;
    private List<String> routeDescription;
    private boolean success;
    private String message;
}


    