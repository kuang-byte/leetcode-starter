package com.hao.leetcode.companies.bolt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseSchedule {
    // courses [0, numCourses - 1]

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // build graph
        // -> Map<Integer, List<Integer>> graph
        // for example
        // b -> c,d
        // a -> e
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] pre : prerequisites) {
            List<Integer> child = graph.getOrDefault(pre[1], new ArrayList<>());
            graph.putIfAbsent(pre[1], child);
            child.add(pre[0]);
        }
        // find circle in the graph
        // DFS
        int[] status = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (!isGraphAcyclic(graph, i, status)) {
                return false;
            }
        }

        return true;
    }

    // status = 0: not-visited, 1: visiting, 2 visited
    // backtracking
    private boolean isGraphAcyclic(Map<Integer, List<Integer>> graph,
                                   int vertex,
                                   int[] status) {
        if (status[vertex] == 1) { // deepest vertex
            return false;
        }
        if (status[vertex] == 2) {
            return true;
        }

        // get all child courses
        // backtracking
        status[vertex] = 1;
        List<Integer> childs = graph.get(vertex);
        if (childs != null) {
            for (int child : childs) {
                if (!isGraphAcyclic(graph, child, status)) {
                    return false;
                }
            }
        }
        status[vertex] = 2;
        return true;
    }
}
