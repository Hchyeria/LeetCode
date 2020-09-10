/**
 * @param {[number[]]} intervals
 * @return {number}
 */

function Interval(start, end) {
    this.start = start
    this.end = end
}


var minMeetingRooms = function(intervals) {
    let length = intervals.length
    if (!length) {
        return 0;
    }
    let arrayStart = intervals.sort((a, b) => a.start - b.start)
    let arrayEnd = intervals.sort((a, b) => a.end - b.end)
    let i = 0, j = 0
    let count = 0
    
    while (i < length && j < length) {
        if (arrayStart[i].start < arrayEnd[j].end) {
            count++;
        } else {
            j++;
        }
        i++;
    }
    return count;
}