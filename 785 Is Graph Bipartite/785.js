/**
 * @param {number[][]} graph
 * @return {boolean}
 */
var isBipartite = function(graph) {
    if (!graph) {
        return false;
    }
    let n = graph.length
    let colors = new Array(n).fill(-1)
    for (let i = 0; i < n; ++i) {
        if (graph[i].length && colors[i] < 0) {
            colors[i] = 0
            if (!helper(graph, i, colors)) {
                return false;
            }
        }
    }
    return true;
};

var helper = function(graph, index, colors) {
    let queue = []
    queue.push(index)
    while (queue.length) {
        let node = queue.shift()
        for (let item of graph[node]) {
            if (colors[item] < 0) {
                colors[item] = colors[node] ^ 1
                queue.push(item)
            } else if (colors[item] != colors[node] ^ 1) {
                return false;
            }
        }
    }
    return true;
}