import sys

######################     Calcul de PGCD    ##################### 
  
def PGCD (nombre1,nombre2) :
    if nombre1 <= 0 or nombre2 <= 0 : raise Exception("Impossible de calculer le PGCD !")
    if nombre1 == 1 or nombre2 == 1 : return 1
    if nombre1 == nombre2 : return nombre1
    if nombre1 < nombre2 : return PGCD (nombre1, nombre2-nombre1)
    return PGCD (nombre2, nombre1-nombre2)

###################     Méthode de Kasiski    ################### 

# Cette fonction exécute la méthode de Kasiski pour trouver la 
# longueur de clé d'un chiffrement de Vigenère. Elle prend à chaque
# fois en paramètre un message, une longueur de mot initiale de 2
# qui augmentera au fil de la méthode, et une liste des distances 
# trouvées entre chaque longueur de mot.

def DecodeVigenereLongueurCle (message, mot = 2, distances = []) :
    
# 1) On récupère toutes les lettres de l'alphabet en majuscule

    alphabet = "".join([ chr(97+i) for i in range(0,26) ]).upper()
    alphabet = alphabet.upper()

# 2) On parcourt l'intégralité du message afin de récupérer les
# répétitions de chaînes de caractère identiques. 

    dictionnaire = {}
    for i in range (0, len(message)-2):
        t = message [i:i+mot]
        if t in dictionnaire: 
            dictionnaire[t].append (i)
        else:
            dictionnaire[t] = [i]
    for t in dictionnaire:
        if len(dictionnaire[t]) > 1:
            print(t, dictionnaire[t])

# 3) On récupère pour chaque groupe de lettres identiques la
# distance entre la position de chaque groupe de caractères
# respective. 

    distance = []
    for d in dictionnaire :
        position = dictionnaire[d]
        if len (position) > 1 :
            for i in range (0, len (position)-1) :
                distance.append( position[i+1] - position [i] )
    for dist in distance:
        distances.append(dist)

# 4) Après avoir trouvé les répétitions et leur position, on
# réitère la fonction, en passant en paramètre une longueur
# de mot supérieure à la valeur précédente, ainsi que les
# distances calculées précédemment. On admet qu'une
# clé de chiffrement ne peut pas être supérieure à 10 lettres,
# on s'arrête aux mots de 10 caractères.

    if mot <= 10 :
        return DecodeVigenereLongueurCle (message, mot+1, distances)
    else:

        # 5) Une fois toutes les distances récupérées, on calcule le
        # PGCD de chaque distance deux par deux jusqu'à la fin de la
        # liste, on enregistre le résultat dans un dictionnaire de 
        # valeurs suspectes d'être la longueur de la clé.

        # Puis on réeffectue l'opération en supprimant une valeur de
        # la liste des distances, jusqu'à qu'il n'en reste qu'une. Si
        # un PGCD a déjà été découvert, on incrémente sa valeur dans 
        # le dictionnaire de un.

        longueurs = {}
        while len(distances) > 1:
            longueur = PGCD (distances[0], distances[1])
            for distance in distances:
                longueur = PGCD (longueur, distance)

            if longueur in longueurs:
                longueurs[longueur] = longueurs[longueur]+1
            else:
                longueurs[longueur] = 1
            del distances[0]

        # 6) On instancie à 0 les occurences de longueurs supérieurs à
        # 10, ou bien égales à 1.

        for longueur in longueurs:
            if longueur > 10 or longueur == 1:
                longueurs[longueur] = 0
        
        # 7) On récupère toutes les occurences de chaque suspections de
        # clé, on les trie dans l'ordre décroissant pour avoir la plus
        # grande occurence de longueur de clé en tête de la liste. On 
        # vérifie bien que cette valeur existe dans le dictionnaire, et
        # on l'affiche. 
        # Il se peut qu'il y ait plusieurs longueursde clé possibles, 
        # alors on assure de bien afficher toutes les valeurs possibles.

        resultats = list(longueurs.values())
        resultats.sort(reverse=True)
        for longueur in longueurs:
            if longueurs[longueur] == resultats[0]:
                print("La longueur de la clé est",longueur)

message = sys.stdin.readline().strip()
print(DecodeVigenereLongueurCle(message))

    
