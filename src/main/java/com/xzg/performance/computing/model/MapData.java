package com.xzg.performance.computing.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @projectName: high-performance-computing-demo
 * @package: com.xzg.performance.computing.model
 * @className: MapData
 * @author: xzg
 * @description: TODO
 * @date: 16/11/2024-下午 5:11
 * @version: 1.0
 */
@Data
public class MapData {
    private Map<String, MapPoint> points;
    private List<MapRoad> roads;
}



    