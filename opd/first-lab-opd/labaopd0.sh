#!/bin/bash

echo -e '-- Выполнил Ковалев Руслан\n'
#Init
mkdir lab0
cd lab0
echo -e '-- Step 1\n--'

mkdir drifloon4
cd drifloon4
touch quagsire
touch gabite
touch magikarp
cd ..

mkdir jynx6
cd jynx6
mkdir lanturn
mkdir spheal
touch gliscor
touch cinccino
cd ..

touch linoone6
mkdir mienshao2
cd mienshao2
mkdir wormadam
mkdir shellos
touch electrike
mkdir purrloin
cd ..

touch persian0
touch unfezant0

echo -e 'Способности Tail Whip Water Gun Mud Sport Mud Shot Slam Mud\nBomb Amnesia Yawn Earthquake Rain Dance Haze Mist Muddy\nWater' >> drifloon4/quagsire
echo -e 'weigth=123.5 height=55.0 atk=9 def=7' >> drifloon4/gabite
echo -e 'Развитые\nспособности Rattled' >> drifloon4/magikarp
echo -e 'satk=5 sdef=8 spd=10' >> jynx6/gliscor
echo -e 'Тип\nпокемона NORMAL NONE' >> jynx6/cinccino
echo -e 'Способности Last Chance Pickup\nGluttony' >> linoone6
echo -e 'Способности Tackle Thunder Wave Leer Howl Quick\nAttack Spark Odor Sleuth Bite Thunder Fang Roar Discharge Charge Wild\nCharge Thunder' >> mienshao2/electrike
echo -e 'Тип диеты Carnivore' >> persian0
echo -e 'Тип покемона\nNORMAL FLYING' >> unfezant0

echo -e '-- Completed\n'

#Этап 2 
echo -e '-- Step 2\n--'

chmod u=rx,g=wx,o=rwx drifloon4
chmod 400 ./drifloon4/quagsire
chmod u-rwx,g=r,o=rw ./drifloon4/gabite
chmod u=r,g-rwx,o=r ./drifloon4/magikarp
chmod u=rx,g=rwx,o=wx jynx6
chmod u=rwx,g=rx,o=w ./jynx6/lanturn
chmod u=rx,g=x,o=wx ./jynx6/spheal
chmod 404 ./jynx6/gliscor
chmod u=rw,g=w,o-rwx ./jynx6/cinccino
chmod u=rw,g=r,o-rwx linoone6
chmod 555 mienshao2
chmod u=wx,g=wx,o=rx ./mienshao2/wormadam
chmod 307 ./mienshao2/shellos
chmod 044 ./mienshao2/electrike
chmod u=wx,g=wx,o=rx ./mienshao2/purrloin
chmod u=rw,g-rwx,o-rwx persian0
chmod 046 unfezant0

echo -e '-- Completed\n'

echo -e '-- Step 3\n--'
#Этап 3
ln -s ../persian0 drifloon4/quagsirepersian 
cp -r drifloon4 mienshao2/purrloin
cp linoone6 mienshao2/purrloin
cat drifloon4/gabite jynx6/gliscor > linoone6_26
ln persian0 drifloon4/magikarppersian
ln -s drifloon4 Copy_34
cat unfezant0 > mienshao2/electrikeunfezant

echo -e '-- Completed\n'

echo -e '-- Step 4\n--'
#Этап 4
find . -type f -name '*6' -print0 | xargs -0 wc -l | sort -n
find . -type f -print0 | xargs -0 stat -c "%Y %n" | sort -n | tail -n 2
cat drifloon4/* | sort 
find . -type f -name '*n' -print0 | xargs -0 wc -c | sort -n
find . -type f -iname 's*' -exec ls -ltu {} + 2>/dev/null | head -n 4
find . -type f -iname '*on*' -exec ls -ltr {} + 2>/dev/null | head -n 3

echo -e '-- Completed\n'

echo -e '-- Step 5\n--'
#Этап 5 (Заключительный)

rm ./linoone6

chmod u+w ./drifloon4/quagsire
rm ./drifloon4/quagsire

rm Copy_*
rm drifloon4/magikarppersi*

# Рекурсивно выдал права для удаления всех файлов
chmod -R u+w mienshao2
rm -r ./mienshao2


echo -e '-- Completed\n'

echo -e '-- Программа выполнилась'



