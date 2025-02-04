def csali(path):
    # if not isinstance(path, str): 
    if type(path) is not str:
        return None
    return len(path)

# print(csali("→→→↑↑→↓→")) # 8
# print(csali(8)) # None