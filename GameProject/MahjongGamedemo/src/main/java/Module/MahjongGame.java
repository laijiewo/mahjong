import numpy as np

# 设置随机种子以确保结果的可重复性
np.random.seed(0)

# 生成100个包含100个随机整数的1-D数组
arrays = [np.random.randint(1, 1000, size=100) for _ in range(100)]

# 打印生成的数组
for i, arr in enumerate(arrays, 1):
    print(f"Array {i}: {arr}")