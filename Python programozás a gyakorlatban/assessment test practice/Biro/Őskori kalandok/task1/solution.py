class Idogep:
    def __init__(self, vissza_ido=1000):
        self.vissza_ido = vissza_ido
    
    def __str__(self):
        return f"Az idogep ennyit fog visszautazni az idoben: {self.vissza_ido}"

idoggep = Idogep(70000000)
# idoggep2 = Idogep(vissza_ido=70000000)