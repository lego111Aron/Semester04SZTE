def benepesites(pirates:list[str])->dict:
    pirateShips_redundant = [pirate.split(';')[1] for pirate in pirates]
    pirateShips = {}

    for pirateShip in pirateShips_redundant:
        if pirateShip not in pirateShips:
            pirateShips[pirateShip] = 0
        pirateShips[pirateShip] += 1

    return pirateShips

print(benepesites(["Zoltan, a veszedelmes;Viharlovag", "Panni, a veszedelmes;Tengeri sárkány", "David, a veszedelmes;Tengeri sárkány"]))