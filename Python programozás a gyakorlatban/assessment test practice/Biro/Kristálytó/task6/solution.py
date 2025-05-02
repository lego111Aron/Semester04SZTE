def tunderek(sizeOfGroups:list)->int:
    sumAll = 0
    for i in sizeOfGroups:
        sumAll += int(i)
    return sumAll

# print(tunderek([4,1,"2",3]))