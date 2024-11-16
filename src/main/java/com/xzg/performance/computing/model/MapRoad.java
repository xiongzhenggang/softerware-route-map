package com.xzg.performance.computing.model;

import lombok.Data;

/**
 * @projectName: high-performance-computing-demo
 * @package: com.xzg.performance.computing.model
 * @className: MapRoad
 * @author: xzg
 * @description: TODO
 * @date: 16/11/2024-下午 5:10
 * @version: 1.0
 */
@Data
public class MapRoad {
    private String start;
    private String end;
    private String name;
    private double length;
    private String type;  // 道路类型
}

    