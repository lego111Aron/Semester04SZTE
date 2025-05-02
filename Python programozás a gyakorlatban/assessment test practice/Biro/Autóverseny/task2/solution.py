class Pilota:
    def __init__(self, _nev, _skill=80):
        self._nev = _nev
        self._skill = _skill
    
    @property
    def nev(self):
        return self._nev
    @property
    def skill(self):
        return self._skill
    
    @nev.setter
    def nev(self, ujNev):
        self._nev = ujNev

    @skill.setter
    def skill(self, ujSkill):
        self._skill = ujSkill

class Csapat:
    def __init__(self, nev):
        self.nev = nev
        self.pilotak = []
    
    def __iadd__(self, pilota:Pilota):
        if type(pilota) is not Pilota:
            raise TypeError("A csapatba csak pilotak johetnek, Axerwaliakok nem!")
        if pilota.skill < 80:
            raise ValueError("A csapatba csak megfelelo kepessegu pilotak johetnek!")
    
        self.pilotak.append(pilota)
        return self

    def __str__(self):
        return ', '.join(pilota.nev for pilota in self.pilotak)
    
    def __float__(self):
        if len(self.pilotak) == 0:
            raise ValueError("A csapat ures!")
        return sum(pilota.skill for pilota in self.pilotak) / len(self.pilotak)

# cs = Csapat("a")
# p1 = Pilota("pilota1", 90)
# # p2 = Pilota("pilota2", 80)
# p2 = 5

# cs+= p1
# cs+= p2

# print(cs)
