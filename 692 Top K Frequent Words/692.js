/**
 * @param {string[]} words
 * @param {number} k
 * @return {string[]}
 */
var topKFrequent = function(words, k) {
    const wordCounts = words.reduce((a, b) => {
      a[b] ? a[b]++ : a[b] = 1;
      return a;
    }, {});
    return Object.keys(wordCounts).sort((a, b) => {
      if (wordCounts[a] > wordCounts[b]) return -1;
      if (wordCounts[b] > wordCounts[a]) return 1;
      else {
        return a.localeCompare(b);
      }
    }).slice(0, k);
  };

  /**
 * @param {string[]} words
 * @param {number} k
 * @return {string[]}
 */
var topKFrequent = function(words, k) {
    let hashMap = words.reduce((acc, val) => {
        acc.set(val, (acc.get(val) || 0) + 1)
        return acc;
    }, new Map())
    let res = []
    for (let k of hashMap.keys()) {
        res.push(k)
    }
    res.sort((a, b) => {
        if (hashMap.get(a) === hashMap.get(b)) {
            return a.localeCompare(b);
        } else {
            return hashMap.get(b) - hashMap.get(a)
        }
    })
    return res.slice(0, k);

};