# Industrial Nexus 0.1 (Minecraft 1.12.2 Forge)

## O que ja faz (v0.1)
- **Controlador Nexus** (bloco): guarda ate 10.000.000 FE. Recebe energia de qualquer fonte FE (cabos/conectores do Immersive Engineering, geradores, etc.). Clique direito mostra a energia. Em criativo, agachado e de mao vazia, enche-o (para testes).
- **Carregador Sem Fios** (item): enquanto estiver no inventario, recarrega itens que usem FE (inventario, armadura, mao esquerda) a partir dos Controladores Nexus num raio configuravel (padrao 64 blocos).
- Deteta Immersive Engineering, HBM e TFC sem os exigir (o mod carrega sem eles).
- Configuracao em `config/industrialnexus.cfg` depois do primeiro arranque.

## Limites desta versao (honestidade)
- O alcance so funciona com o Controlador em chunks carregados (perto de ti, ou chunk-loaded).
- So recarrega itens FE. Itens do HBM que usem HE (ainda) nao sao recarregados.
- Ainda NAO tem: multiblocos, ferramentas, upgrades, armadura, fornalha de inducao.

## Como obter o .jar (sem instalar nada)
1. Extrai este .zip num PC.
2. Cria conta gratuita em github.com. Clica em **New repository**, nome `industrial-nexus`, **Create repository**.
3. Na pagina do repositorio, clica **uploading an existing file** e arrasta TODO o conteudo da pasta extraida (incluindo a pasta `.github`). Clica **Commit changes**.
   - Se a pasta `.github` nao subir: **Add file > Create new file**, nome `.github/workflows/build.yml`, cola o conteudo do ficheiro build.yml deste projeto e faz Commit.
4. Abre o separador **Actions**. O "Build mod" arranca sozinho (5 a 10 min). Se nao, clica **Run workflow**.
5. Quando ficar com visto verde, abre essa execucao e, em **Artifacts**, descarrega `IndustrialNexus-jar`. Extrai o zip: la dentro esta o .jar.
6. No CurseForge: instancia 1.12.2 Forge > ... > **Open Folder** > pasta `mods` > cola o .jar. Abre o jogo.
7. Se aparecer uma cruz vermelha, abre a execucao, copia o erro e envia ao Claude.

## Testar no jogo
Separador criativo "Industrial Nexus": coloca um Controlador, enche-o (agachado, mao vazia, criativo), poe o Carregador Sem Fios no inventario com uma ferramenta FE (ex.: Drill do IE) e afasta-te.
