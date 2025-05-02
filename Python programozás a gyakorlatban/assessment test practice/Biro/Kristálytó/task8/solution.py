def templomtanc(valuables:list[str])->list:
    if valuables == None:
        return None

    VOWELS = "aáeéiíoóöőuúüű"
    solution = []
    
    for valuable in valuables:
        if "szent" in valuable.lower():
            solution.append(valuable)
            continue
        if len(valuable) >= 5:
            valuable_noVOWELS = ''.join([c for c in valuable if c.lower() not in VOWELS])
            solution.append(valuable_noVOWELS)
    
    solution = [item.strip() for item in solution]
    solution.sort()
    return solution

print(templomtanc(None))
print(templomtanc(["ttSzent", "alma", "bicikli"]))