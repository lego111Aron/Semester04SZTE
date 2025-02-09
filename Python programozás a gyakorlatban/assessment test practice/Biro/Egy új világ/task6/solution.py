# from typing import Dict, List

def foci(trains:dict[str,list[float]]):
    trainsAverageScore = {}
    for train in trains:
        trains[train].remove(min(trains[train]))
        trains[train].remove(max(trains[train]))

        trainsAverageScore[train] = 0
        for score in trains[train]:
            trainsAverageScore[train] += score
        trainsAverageScore[train] /= len(trains[train])
    
    return max(trainsAverageScore, key=trainsAverageScore.get)


print(foci({ "KX8-3YZ6": [0.17, 0.88, 0.53, 0.63], "VF3-1XJ7": [0.55, 0.74, 0.23, 0.11], "SD1-7MV8": [0.54, 0.99, 0.53, 0.51, 0.54] }))
