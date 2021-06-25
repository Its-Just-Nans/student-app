# student-app

<details>
<summary>Click to expand</summary>

- [Principales activités](#principales-activités)
- [Répartition des tâches](#répartition-des-tâches)
- [Temps de travail](#temps-de-travail)
- [Source d'inspiration](#source-dinspiration)
- [Details techniques](#details-techniques)
- [Class `Quiz`](#class-quiz)
- [Class `urlRequest`](#class-urlrequest)
- [Utilisation des `sharedPreferences`](#utilisation-des-sharedpreferences)
- [API documentation](#api-documentation)
  - [Créé un nouvel utilisateur](#créé-un-nouvel-utilisateur)
  - [Connaître le quiz d'un utilisateur](#connaître-le-quiz-dun-utilisateur)
  - [Connaître le nombre de quiz créé par un utilisateur](#connaître-le-nombre-de-quiz-créé-par-un-utilisateur)
  - [Récupérer le quiz](#récupérer-le-quiz)
- [Utilisation des éléments Androids](#utilisation-des-éléments-androids)
- [Avantages et inconvénients](#avantages-et-inconvénients)
  - [Inconvénients](#inconvénients)
  - [Avantages](#avantages)
- [Difficultés](#difficultés)
- [Améliorations possible du programme](#améliorations-possible-du-programme)
- [Apport du module](#apport-du-module)
- [Captures d'écran](#captures-décran)

</details>

---

Cette application est une application pour les étudiants, mais elle est cibler sur le fait de jouer et créer des quizs.

Les quizs sont stockés sur le serveur, et donc ils peuvent être récupérer par tous les utilisateurs, ainsi, tous les utilisteurs peuvent jouer aux quizs

## Principales activités

- Passer un quiz
- Créer un quiz
- Voir les news
- Accéder aux settings (changer de compte, voir des statistiques)

> Noter qu'on peut créer très facilement une activité et la rajouter au menu

## Répartition des tâches

- Nans : idée, création du système
- Maxence : aide de code
- Jules : onglet système

## Temps de travail

- Nans
  - en TP : apprentissage d'Android, chez lui
  - chez lui : application des apprentissages (donc pas mal de temps)

- Maxence
  - en TP : apprentissage du JAVA et d'Android
  - chez lui : création de code (quand AndroidStudio marchait)
- Jules
  - en TP : apprentissage du JAVA et d'Android
  - chez lui : création de code (quand AndroidStudio marchait)

## Source d'inspiration

- Application de quizs créé par Nans en JavaScript
- L'application SoloLearn

## Details techniques

- utilisation des sharedPreferences pour stocker l’id et le mot de passe du User
- utilisation du serveur comme API et donc de stockage des données
- création d’une Class `Quiz` qui permet de stocker un quiz
- création d’une Class `urlRequest` qui permet d’effectuer des requêtes très simplement
  - avec un retour en JSON
  - avec un retour en String

## Class `Quiz`

```java
private String description;
private String title;
private String id;
private String creatorId;
private ArrayList<String> lessons;
private ArrayList<Integer> reponses;
private ArrayList<ArrayList<String>> choices;
private ArrayList<String> questions;
```

> Légende :
>
> - `description` est la description
> - `title` est le titre du quiz
> - `id` est l'ID du quiz
> - `creatorId` l'ID du créateur du quiz
> - `lessons` est un tableau de leçons, on accède à la première leçon avec l'index 0
> - `reponses` est un tableau d'entier, cela permet de connaître l'index de la bonne réponse dans `choices`
> - `choices` est un tableau de tableau, l'index 0 contient donc un tableau des réponses
> - `questions` est un tableau de questions, on accède à la première question avec l'index 0

## Class `urlRequest`

Cette classe permet de faire une requête très simplement

Avec un retour en `String` :

```java
String urlRequest("action=get&obj=valeur&idU="+this.creatorId).executeRequest();
```

Avec un retour en `JSONObject` :

```java
JSONObject urlRequest("action=get&obj=valeur&idU="+this.creatorId).executeRequestJSON();
```

Cela permet de faire des requêtes très facilement

> il ne faut pas oublier de mettre la bonne ligne dans le `AndroidManifest.xml`

## Utilisation des `sharedPreferences`

Exemples d'utilisations :

```java
// lire les données
SharedPreferences settings = getSharedPreferences("A", 0);
String id_user = settings.getString(getString(R.string.id_user), "");
String password = settings.getString(getString(R.string.password), "");
// le deuxième paramêtre est la valeur par défaut
```

```java
// écrire les données
SharedPreferences settings = getSharedPreferences("A", 0);
SharedPreferences.Editor editor = settings.edit();
editor.putString(getString(R.string.id_user), newID);
editor.putString(getString(R.string.password), default_password);
editor.apply();
```

## API documentation

Nous avons utilisé seulement quelques URLs :

### Créé un nouvel utilisateur

```url
"action=create&nom=studentAccount&prenom=account&mail=m&mdp="+default_password
```

> Legende :
>
> - cette URL sert à créer un compte sur le serveur
> - cela renvoie un ID sous forme de string

### Connaître le quiz d'un utilisateur

```url
"action=get&obj=valeur&idU="+this.creatorId
```

> Legende :
>
> - Cette URL permet de connaître toutes les valeurs du compte `this.creatorId` et donc de récupérer les valeurs du quiz

### Connaître le nombre de quiz créé par un utilisateur

```url
"action=get&obj=valeur&type=quiz&idU="+id_user
```

> Legende :
>
> - Cette URL permet de connaître les valeurs de type `quiz` de l'utilisateur d'id `id_user`

### Récupérer le quiz

```url
"action=get&type=quiz&obj=valeur"
```

> Legende :
>
> - Cette URL permet de connaître les valeurs de type `quiz`

## Utilisation des éléments Androids

- ListView (menu démarrage)
- Recycler view (liste des quizs)
- TextView
- PlainText (input des textes)
- RadioGroup
- RadioButton
- Button
- WebView
- Fragments (pour le quiz, agit comme une "sous-activité")
- urlConnection
- JSONObject

## Avantages et inconvénients

### Inconvénients

- interface en français
- une seule plateforme (android)

### Avantages

- Multijoueurs
- Multi-langues pour les quizs
- possibilité de mettre plus d’activités très aisément

## Difficultés

- Certains membres du groupe n’étaient pas très à l’aise avec le Java
- Certains membres du groupe n’avaient pas la possibilité de travailler chez eux
- Utilisation du ReclyclerView (utilisation d'un tutoriel et du cours pour le mettre en place, le code a été compris)

## Améliorations possible du programme

- Éditer un quiz
- Rechercher un quiz

## Apport du module

- apprentissage d’android
- utilisation variée de composants (listView, Input, RecyclerView, Fragment, urlConnection…)
- (re-) Apprentissage du JAVA  pour certains

## Captures d'écran

Des captures d'écran sont disponbile dans le dossier `screenshots`
