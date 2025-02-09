def verseny(winnerFerns:list[str]):
    numberOfTimes = {}
    for winnerFern in winnerFerns:
        if winnerFern not in numberOfTimes:
            numberOfTimes[winnerFern] = 1
            continue
        numberOfTimes[winnerFern] += 1
    
    return sum((numberOfTime == 2) for numberOfTime in numberOfTimes.values())

print(verseny(["Dolphin Dart", "Coral Comet", "Aqua Racer", "Coral Comet", "Coral Comet", "Wave Warrior", "Wave Warrior", "Fin Flash", "Dolphin Dart"]))