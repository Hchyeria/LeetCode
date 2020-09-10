class MyCalendar:
    def __init__(self):
        self.calendar = []

    def book(self, start: int, end: int) -> bool:
        index, lower = self.binarySearch(end)
        if lower and lower[1] > start:
            return False
        # if not index, means index != None and 0!!!
        if index == -1:
            self.calendar.insert(0, (start, end))
        else:
            self.calendar.insert(index + 1, (start, end))
        return True
    
    def binarySearch(self, end):
        if not self.calendar:
            return -1, None
        l = 0
        r = len(self.calendar) - 1
        while l < r - 1:
            mid = l + (r - l) // 2
            if self.calendar[mid][0] < end:
                l = mid
            else:
                r = mid - 1
        if self.calendar[r][0] < end:
            return r, self.calendar[r]
        if self.calendar[l][0] < end:
            return l, self.calendar[l]
        return -1, None
            
            




# Your MyCalendar object will be instantiated and called as such:
# obj = MyCalendar()
# param_1 = obj.book(start,end)