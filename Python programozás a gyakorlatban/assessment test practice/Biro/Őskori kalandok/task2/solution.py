class Dinoszaurusz:
    def __init__(self, _fajta:str, _magassag):
        self._fajta = _fajta
        self._magassag = abs(_magassag)
        self._veszelyes = _fajta.endswith("rex") or _magassag > 500
    
    @property
    def fajta(self):
        return self._fajta
    @property
    def magassag(self):
        return self._magassag
    @property
    def veszelyes(self):
        return self._veszelyes
    
    @fajta.setter
    def fajta(self, ujFajta):
        self._fajta = ujFajta
    @magassag.setter
    def magassag(self, ujMagassag):
        if ujMagassag > 0:
            self._magassag = ujMagassag
    @veszelyes.setter
    def veszelyes(self, ujVeszelyes):
        self._veszelyes = ujVeszelyes
    
    def __iadd__(self, taplalek):
        self._magassag += taplalek
        # self._veszelyes = self._veszelyes or self._magassag > 500
        return self
    
    def __eq__(self, other):
        if type(other) != Dinoszaurusz:
            return False
        return self._fajta == other._fajta and self._magassag == other._magassag and self._veszelyes == other._veszelyes
    