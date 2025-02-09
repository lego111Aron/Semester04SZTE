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
    
    @skill.setter
    def skill(self, ujSkill):
        self._skill = ujSkill
