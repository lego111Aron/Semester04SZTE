def uszoverseny(depth_me:int, depth_Mozes:int):
    if depth_me > depth_Mozes:
        print("Az uj kacsa diadalmaskodott")
    elif depth_me < depth_Mozes:
        print("Mozes uszasban verhetetlen")
    else:
        print("Mindket kacsa egyforman jo uszo")

# uszoverseny(10, 20) # "Mozes uszasban verhetetlen"
# uszoverseny(20, 10) # "Az uj kacsa diadalmaskodott"
# uszoverseny(10, 10) # "Mindket kacsa