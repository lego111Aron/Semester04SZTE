def meow_szobor(persianText:str, lowerCase:bool)->str:
    if lowerCase:
        persianText = persianText.lower()
    return persianText[::-1]

# print(meow_szobor("aaaBBB", True))