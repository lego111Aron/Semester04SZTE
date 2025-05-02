def nyertes_korok(firstTeamPoints:list, secondTeamPoints:list):
    fTP_len = len(firstTeamPoints)
    if (fTP_len != len(secondTeamPoints) or fTP_len == 0):
        return -1
    
    solution = 0
    for i in range(fTP_len):
        if firstTeamPoints[i] > secondTeamPoints[i]:
            solution += 1
    return solution

# print(nyertes_korok([30, 50, 10, 80, 100, 40], [60, 20, 10, 20, 30, 20]))
# print(nyertes_korok([70, 40, 50, 80, 0], [10, 90, 100, 20]))
# print(nyertes_korok([], []))