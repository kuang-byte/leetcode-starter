package com.hao.leetcode.companies.bolt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseScheduleII {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] pre : prerequisites) {
            List<Integer> childs = graph.getOrDefault(pre[1], new ArrayList<>());
            childs.add(pre[0]);
            graph.putIfAbsent(pre[1], childs);
        }

        List<Integer> result = new ArrayList<>();
        int[] status = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (isGraphCyclic(graph, i, status, result)) {
                return new int[0];
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public boolean isGraphCyclic(Map<Integer, List<Integer>> graph,
                                 int vertex,
                                 int[] status,
                                 List<Integer> result) {
        if (status[vertex] == 1) return false;
        if (status[vertex] == 2) return true;

        status[vertex] = 1;
        for (Integer child : graph.getOrDefault(vertex, new ArrayList<>())) {
            if (isGraphCyclic(graph, child, status, result)) {
                return true;
            }
        }

        status[vertex] = 2;
        result.add(0, vertex);
        return false;
    }
}
