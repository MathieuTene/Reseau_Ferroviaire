<div align="center">
  <h1 align="center"> 🚂 Simulateur de Réseau Ferroviaire </h1>
  <p align="center">
    <strong>Application Java/JavaFX de modélisation et simulation de trafic ferroviaire.</strong>
  </p>

  <p align="center">
    <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white" alt="Java" />
    <img src="https://img.shields.io/badge/JavaFX-000000?style=for-the-badge&logo=java&logoColor=white" alt="JavaFX" />
    <img src="https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white" alt="Gradle" />
    <img src="https://img.shields.io/badge/Status-En_cours-success?style=for-the-badge" alt="Status" />
  </p>
</div>

<br />

## 📖 À propos du projet

Ce projet a été réalisé dans le cadre de la formation d'ingénieur en Informatique à l'**EILCO**. Il s'agit du développement progressif d'un mini-simulateur de réseau ferroviaire en Java, doté d'une interface graphique interactive réalisée avec **JavaFX**.

L'objectif principal est de mettre en pratique les concepts avancés de la Programmation Orientée Objet : *encapsulation, classes abstraites, héritage, interfaces, polymorphisme et séparation des responsabilités (Modèle/Vue/Contrôleur)*.

## ✨ Fonctionnalités actuelles

- 🗺️ **Modélisation du réseau :** Création d'une architecture solide comprenant des `Gares`, des `Noeuds` et des `Segments` calculant les distances euclidiennes.
- 🛤️ **Itinéraires :** Gestion de listes ordonnées de segments pour définir le parcours des trains.
- 🚂 **Déplacement physique :** Moteur de simulation (`SimulationEngine`) calculant la position des trains sur les segments en fonction du temps (`dt`) et de leur vitesse.
- 🎨 **Rendu Graphique (JavaFX) :** 
  - Boucle d'animation (`AnimationTimer`) pour le rendu fluide du mouvement.
  - Intégration de ressources graphiques (locomotives et rails).

## 🏗️ Architecture du projet

Le projet est structuré pour garantir une forte séparation des responsabilités :
- `model` : Classes métiers indépendantes de l'affichage.
- `sim` : Moteur de simulation (évolution du modèle à chaque tick).
- `ui` : Interface graphique JavaFX et boucle d'animation.

## 🚀 Installation et exécution

Ce projet utilise **Gradle** et requiert **Java 17**.

1. **Cloner le dépôt :**
   ```bash
   git clone https://github.com/MathieuTene/Simulateur-Reseau-Ferroviaire.git
   ```
2. **Lancer l'application :**
   - Via IntelliJ : `Gradle > Tasks > application > run`
   - Via terminal : `./gradlew run`

## 📈 Repository Card
<p align="center">
  <img src="https://github-readme-stats.vercel.app/api/pin/?username=MathieuTene&repo=Simulateur-Reseau-Ferroviaire&theme=tokyonight" alt="Repository Card" />
</p>
