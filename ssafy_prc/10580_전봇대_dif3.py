def sol(arr):
    ans = 0

    for i in range(len(arr)-1):
        a1, b1 = arr[i]
        for j in range(i+1, len(arr)):
            a2, b2 = arr[j]

            if (a1 < a2 and b1 > b2) or (a1 > a2 and b1 < b2):
                ans += 1

    return ans


for tc in range(int(input())):
    arr = []
    for i in range(int(input())):
        a, b = map(int, input().split())
        arr.append([a, b])

    res = sol(arr)
    print(f'#{tc+1} {str(res)}')