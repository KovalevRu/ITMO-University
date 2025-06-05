import numpy as np
import matplotlib.pyplot as plt

Q = -1424
W = 54

# 1. Для x > 0: y = Q
x1 = np.linspace(0.1, 10, 100)
y1 = np.full_like(x1, Q)

# 2. Для x ≤ Q: y = Q
x2 = np.linspace(-1500, Q, 100)
y2 = np.full_like(x2, Q)

# 3. Для Q < x ≤ 0: y = 3x - W
x3 = np.linspace(Q + 1, 0, 500)
y3 = 3 * x3 - W

plt.figure(figsize=(14, 8))

plt.plot(x1, y1, color='blue', linewidth=2)
plt.plot(x2, y2, color='red', linewidth=2)
plt.plot(x3, y3, color='green', linewidth=2)
 
plt.axvline(x=0, color='gray', linestyle='--', alpha=0.7)
plt.axvline(x=Q, color='purple', linestyle='--', alpha=0.7)


plt.title('')  
plt.xlabel('') 
plt.ylabel('')  


plt.grid(False)
plt.xlim([Q - 50, 10])
plt.ylim([-4500, 100])

plt.show()