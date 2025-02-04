def lakoma():
    bodyParts = {
            "fej":1,
            "test":1,
            "kar":2,
            "lab":2
        }
    
    for i in bodyParts:
        bodyParts[i] *= int(input())
    
    return max(bodyParts, key=bodyParts.get)

# print(lakoma())