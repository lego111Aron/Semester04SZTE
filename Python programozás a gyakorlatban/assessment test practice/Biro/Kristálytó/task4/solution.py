def vizen_jaras(water):
    if type(water) is not str:
        return False
    return water.count('~') >= len(water) / 2

# print(vizen_jaras("~~~___"))
# print(vizen_jaras("~~___"))
# print(vizen_jaras(5))