# 🏦 Gestion des Comptes Bancaires (Java 8 - Console App)

## 📌 Contexte
Une banque souhaite automatiser la gestion de ses comptes.  
Cette application console, développée en **Java 8**, permet de gérer efficacement les **comptes bancaires** et leurs **opérations** (versements, retraits, virements).

---

## 🏗️ Architecture du Projet
- **Couche Présentation (UI/Menu)** → Interaction avec l’utilisateur.
- **Couche Métier** → Règles de gestion (comptes, opérations).
- **Couche Utilitaire** → Fonctions d’aide (validations, formatage).
- **Stockage en mémoire** → `ArrayList` / `HashMap`.

---

## 📂 Structure des Classes

### 🔹 Classe abstraite `Compte`
- Attributs : `code`, `solde`, `listeOperations`
- Méthodes abstraites :  
  - `retirer()`
  - `calculerInteret()`
  - `afficherDetails()`

### 🔹 `CompteCourant` (extends `Compte`)
- Attribut : `decouvert`
- Intérêt = 0
- Règle retrait : solde ≥ -découvert

### 🔹 `CompteEpargne` (extends `Compte`)
- Attribut : `tauxInteret`
- Intérêt = solde × taux
- Règle retrait : solde ≥ montant retiré

### 🔹 Classe abstraite `Operation`
- Attributs : `numero (UUID)`, `date`, `montant`

### 🔹 `Versement` (extends `Operation`)
- Attribut : `source`

### 🔹 `Retrait` (extends `Operation`)
- Attribut : `destination`

---

## ⚙️ Fonctionnalités
✔ Créer un compte (courant ou épargne)  
✔ Effectuer un **versement**  
✔ Effectuer un **retrait**  
✔ Effectuer un **virement** (entre deux comptes)  
✔ Consulter le **solde**  
✔ Lister les **opérations** d’un compte  
