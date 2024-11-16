package com.xzg.performance.computing.model;

import lombok.Data;

import java.util.List;

/**
 * @projectName: high-performance-computing-demo
 * @package: com.xzg.performance.computing.model
 * @className: MapPoint
 * @author: xzg
 * @description: TODO
 * @date: 16/11/2024-下午 5:10
 * @version: 1.0
 */

@Data
public class MapPoint {
    private double x;
    private double y;
    private String name;
    private List<String> line;  // 用于地铁线路
    private String type;         // 点位类型
}

    