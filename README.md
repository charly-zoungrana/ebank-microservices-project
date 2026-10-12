<div align="center">

# E-Bank — Architecture Microservices & Agent IA

**Système bancaire distribué avec Spring Boot, Spring Cloud et Spring AI**

*Module : Systèmes Distribués & DevOps — S5*

![Java](https://img.shields.io/badge/Java-21-334155?style=flat-square)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.1-334155?style=flat-square)
![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2025.1.3-334155?style=flat-square)
![Spring AI](https://img.shields.io/badge/Spring%20AI-2.0.1-334155?style=flat-square)
![Build](https://img.shields.io/badge/Build-Maven-475569?style=flat-square)

</div>

---

## Présentation

**E-Bank** est un projet académique qui construit, étape par étape, un système bancaire fondé sur une **architecture microservices**. Il commence par le socle distribué : services métier, découverte, passerelle et tolérance aux pannes. Il y ajoute ensuite un **assistant conversationnel intelligent** capable d'interroger ces services.

Chaque partie du projet est livrée sur **sa propre branche Git**, avec un rapport dédié dans son `README`.

## Parties du projet

| Partie | Contenu | Branche | Rapport |
|:-:|---|---|---|
| **1** | Customer-Service, EBank-Service, OpenFeign, Circuit Breaker Resilience4J, Gateway, Discovery Service (Eureka), tests Postman et Swagger-UI | [`partie-1`](https://github.com/charly-zoungrana/ebank-microservices-project/tree/partie-1) | [Lire le rapport →](https://github.com/charly-zoungrana/ebank-microservices-project/blob/partie-1/README.md) |
| **2** | Agent IA avec Spring AI, LLM via Groq, mémoire de conversation, serveurs et client **MCP**, bots **Discord** et **Telegram** | [`partie-2`](https://github.com/charly-zoungrana/ebank-microservices-project/tree/partie-2) | [Lire le rapport →](https://github.com/charly-zoungrana/ebank-microservices-project/blob/partie-2/README.md) |

## Vue d'ensemble

```mermaid
%%{init: {'theme':'base','themeVariables':{'fontFamily':'Segoe UI, Helvetica, Arial, sans-serif','fontSize':'14px','primaryColor':'#F1F5F9','primaryBorderColor':'#475569','primaryTextColor':'#0F172A','lineColor':'#64748B','clusterBkg':'#F8FAFC','clusterBorder':'#CBD5E1','edgeLabelBackground':'#FFFFFF'}}}%%
flowchart LR
    subgraph P2["Partie 2 — Agent IA"]
        direction TB
        CH["Discord · Telegram · REST"]
        BOT["ebank-bot<br/><small>Spring AI · client MCP</small>"]
        LLM["LLM<br/><small>Groq</small>"]
        CH --> BOT
        BOT <--> LLM
    end

    subgraph P1["Partie 1 — Socle microservices"]
        direction TB
        GW["Gateway<br/><small>:8888</small>"]
        CS["Customer Service<br/><small>:8081</small>"]
        ES["EBank Service<br/><small>:8082</small>"]
        DS["Discovery<br/><small>Eureka · :8761</small>"]
        GW --> CS
        GW --> ES
        ES -->|"OpenFeign"| CS
        CS -.-> DS
        ES -.-> DS
    end

    BOT -->|"MCP"| CS
    BOT -->|"MCP"| ES

    classDef p1 fill:#F1F5F9,stroke:#475569,color:#0F172A
    classDef p2 fill:#E2E8F0,stroke:#334155,color:#0F172A
    classDef ext fill:#DBE4EE,stroke:#334155,color:#0F172A
    class GW,CS,ES p1
    class CH,BOT p2
    class LLM,DS ext
```

## Stack technique

| Domaine | Technologies |
|---|---|
| Socle | Java 21 · Spring Boot 4.1.1 · Maven |
| Microservices | Spring Data JPA · H2 · Spring Cloud Gateway · Eureka · OpenFeign · Resilience4J |
| Intelligence artificielle | Spring AI 2.0.1 · Groq (`gpt-oss-120b`) · Model Context Protocol |
| Canaux | Discord (JDA) · Telegram Bots API |
| Documentation et tests | springdoc-openapi (Swagger-UI) · Postman · PlantUML · Mermaid |

## Naviguer entre les parties

```bash
git clone https://github.com/charly-zoungrana/ebank-microservices-project.git
cd ebank-microservices-project

git switch partie-1   # socle microservices
git switch partie-2   # agent IA + MCP
```

> Après chaque changement de branche, rechargez les projets Maven et reconstruisez le projet avant de relancer les services.

---

<div align="center"><sub>ZOUNGRANA Charly · Projet réalisé dans le cadre du module Systèmes Distribués & DevOps</sub></div>
