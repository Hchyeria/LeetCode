/**
 * @param {number[]} candidates
 * @param {number} target
 * @return {number[][]}
 */
var combinationSum = function(candidates, target) {
    let res = []
    if (target === 0) {
        return res;
    }
    helper(candidates, target, res, [], 0)
    return res;
};

var helper = function(candidates, target, res, item, index) {
    if (index === candidates.length) {
        if (target === 0) {
            res.push(add(candidates, item))
        }
        return;
    }
    let count = Math.floor(target / candidates[index]);
    for (let i = 0; i <= count; ++i) {
        item.push(i)
        helper(candidates, target - i * candidates[index], res, item, index + 1)
        item.pop()
    }
}

var add = function(candidates, list) {
    let res = []
    for (let i = 0; i < list.length; ++i) {
        // js contact return a new array, not change the origin one
        res = res.concat(new Array(list[i]).fill(candidates[i]))
    }
    return res;
}

// DP
var combinationSum2 = function(candidates, target) {
    let res = []
    if (target === 0) {
        return res;
    }
    candidates.sort((a, b) => a - b)
    let dp = []
    for (let i = 0; i <= target; ++i) {
        res = []
        // write i < candidates.length again ?? excuse me ??? be focus!!
        for (let j = 0; j < candidates.length && candidates[j] <= i; ++j) {
            if (i === candidates[j]) {
                res.push([candidates[j]])
            } else {
                let pre = dp[i - candidates[j]]
                for (let p of pre) {
                    if (candidates[j] <= p[0]) {
                        res.push([j].concat(p))
                    }                 
                }
            }
        }
        dp.push(res)
    }
    return dp[target];
};
