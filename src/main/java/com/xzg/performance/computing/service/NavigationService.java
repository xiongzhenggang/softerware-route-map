package com.xzg.performance.computing.service;

import com.xzg.performance.computing.model.MapData;
import com.xzg.performance.computing.model.MapPoint;
import com.xzg.performance.computing.model.MapRoad;
import com.xzg.performance.computing.model.RouteResult;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @projectName: high-performance-computing-demo
 * @package: com.xzg.performance.computing.service
 * @className: NavigationService
 * @author: xzg
 * @description: TODO
 * @date: 16/11/2024-下午 5:13
 * @version: 1.0
 */
@Service
public class NavigationService {
    private MapData mapData;
    private Graph<String, DefaultWeightedEdge> graph;

    public void loadMapData(MapData data) {
        this.mapData = data;
        buildGraph();
    }

    private void buildGraph() {
        graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
        // 添加顶点
        mapData.getPoints().keySet().forEach(graph::addVertex);
        // 添加边
        for (MapRoad road : mapData.getRoads()) {
            DefaultWeightedEdge edge = graph.addEdge(road.getStart(), road.getEnd());
            if (edge != null) {
                graph.setEdgeWeight(edge, road.getLength());
            }
        }
    }

    public RouteResult calculateRoute(String startPoint, String endPoint) {
        RouteResult result = new RouteResult();

        try {
            DijkstraShortestPath<String, DefaultWeightedEdge> dijkstra =
                    new DijkstraShortestPath<>(graph);
            GraphPath<String, DefaultWeightedEdge> path =
                    dijkstra.getPath(startPoint, endPoint);

            if (path == null) {
                result.setSuccess(false);
                result.setMessage("未找到可行路径");
                return result;
            }

            // 获取路径顶点
            List<String> vertices = path.getVertexList();
            result.setPath(vertices);
            result.setTotalDistance(path.getWeight());

            // 生成路径描述
            List<String> routeDescription = new ArrayList<>();
            for (int i = 0; i < vertices.size() - 1; i++) {
                String current = vertices.get(i);
                String next = vertices.get(i + 1);
                MapPoint currentPoint = mapData.getPoints().get(current);
                MapPoint nextPoint = mapData.getPoints().get(next);

                String description = String.format("从 %s 到 %s",
                        currentPoint.getName() != null ? currentPoint.getName() : current,
                        nextPoint.getName() != null ? nextPoint.getName() : next
                );
                routeDescription.add(description);
            }

            result.setRouteDescription(routeDescription);
            result.setSuccess(true);

        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("路径计算出错: " + e.getMessage());
        }

        return result;
    }
}

    