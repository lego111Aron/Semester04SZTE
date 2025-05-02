def szekem(sCCI:int)->str: # sCCI = serialContinuityChairIdentifier
    sCCI-=1
    right = (sCCI // 7 ) % 2 == 0
    
    side = "jobb" if right else "bal"
    row = sCCI // 14 + 1
    number = sCCI % 7 + 1
    
    if right:
        number = 8 + number * -1

    return f"{row}. sor, {side} {number}. szek"

print(szekem(21))