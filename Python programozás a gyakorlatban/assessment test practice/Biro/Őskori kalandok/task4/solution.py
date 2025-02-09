class Loot:
    def __init__(self, nev, meret = 1, ertek = 1):
        self.nev = nev  # a loot neve (pl. 'ősgyökér')
        self.meret = meret  # a loot mérete (cm^3-ben, a táskába pakoláshoz hasznos)
        self.ertek = ertek  # a loot értéke (hány $-ért lehet eladni)

    def __str__(self):
        return f"{self.nev} ({self.meret}) -> {self.ertek} $"

class Taska:
    def __init__(self, kapacitas):
        self.kapacitas = kapacitas
        # self.lootok = [None] * kapacitas
        self.lootok = []
    
    def targyat_elrak(self, loot):
        currentLootVolume = sum(i.meret for i in self.lootok)  # Összes tárgy méretének kiszámítása
        if currentLootVolume + loot.meret <= self.kapacitas:
            self.lootok.append(loot)

class VeryBigTaska(Taska):
    def __init__(self):
        super().__init__(None)

    def targyat_elrak(self, loot):
        self.lootok.append(loot)
    
    def __add__(self, other):
        uj_taska = VeryBigTaska()
        uj_taska.lootok = self.lootok + other.lootok  # Mindkét táska lootjai egy listában
        return uj_taska