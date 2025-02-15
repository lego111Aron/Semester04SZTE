def hogolyo_csata(korok):
    statisztikak = {}
    
    for kor in korok:
        for jatekos, adatok in kor.items():
            if jatekos not in statisztikak:
                statisztikak[jatekos] = {'eldobott_hogolyok': 0, 'talalt': 0, 'fejtalalat': 0}
            statisztikak[jatekos]['eldobott_hogolyok'] += adatok.get('eldobott_hogolyok', 0)
            statisztikak[jatekos]['talalt'] += adatok.get('talalt', 0)
            statisztikak[jatekos]['fejtalalat'] += adatok.get('fejtalalat', 0)
    
    return statisztikak

def hogolyo_pontossag(statisztikak):
    for jatekos, adatok in statisztikak.items():
        eldobott_hogolyok = adatok['eldobott_hogolyok']
        talalt = adatok['talalt']
        if eldobott_hogolyok > 0:
            adatok['pontossag'] = talalt / eldobott_hogolyok
        else:
            adatok['pontossag'] = 0
    return statisztikak

def hogolyo_piros_lap(statisztikak):
    piros_lapot_kapok = []
    
    for jatekos, adatok in statisztikak.items():
        fejtalalat = adatok['fejtalalat']
        talalt = adatok['talalt']
        pontossag = adatok.get('pontossag', 0)
        
        if pontossag >= 0.7 and talalt > 0 and fejtalalat / talalt >= 0.5:
            piros_lapot_kapok.append(jatekos)
    
    return piros_lapot_kapok