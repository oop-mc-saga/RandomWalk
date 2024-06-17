import matplotlib.pyplot as plt
import math
"""
This python code is used to draw the histogram of the random walk


"""


def normal(x: float, m: float, sigma: float) -> float:
    """
    Normal distribution function

    Parameters
    ----------
    x: x random variable
    m: mean
    sigma: standard deviation
    
    Returns
    -------
    normal distribution value
    """
    return (1 / math.sqrt(2 * math.pi) / sigma) * math.exp(
        -(x - m) * (x - m) / 2 / sigma / sigma)

def readData(filename: str) -> tuple[list[float], list[float]]:
    """
    Read data from file

    Parameters
    ----------
    filename: file name

    Returns
    -------
    xl: x list
    yl: y list
    """
    with open(filename, "r") as f:  # read data from file
        lines: list[str] = f.readlines()
    xl: list[float] = list()
    yl: list[float] = list()
    for l in lines:  # split data into x and y and store them in list
        x, y = l.split()
        xl.append(float(x))
        yl.append(float(y))
    return xl, yl

def drawHistogram(
    prefix: str,
    xmin: float, xmax: float, mean: float, sigma: float,
    w:float=1.5, t:int=1000, n:int=1024, toPDF:bool = False) -> None:
    """
    Draw histogram

    Parameters
    ----------
    prefix: prefix for file name
    xmin: x minimum
    xmax: x maximum
    mean: mean of the distribution
    sigma: standard deviation of the distribution
    w: width of the bar
    t: number of steps for showing parameter in title
    n: number of points for drawing the normal distribution
    """
    dataFilename = f"{prefix}-output-{t}.txt"  # file name for input data
    xl, yl = readData(dataFilename)
    fig, ax = plt.subplots()#type: ignore
    ax.bar(xl, yl, w)
    ax.set_title(f"Histogram for {prefix} Case ($t={t}$)")
    ax.set_xlabel("$x$")
    xld: list[float] = [(xmax - xmin) * x / n + xmin for x in range(n)]
    yld: list[float] = [normal(x, mean, sigma) for x in xld]
    ax.plot(xld, yld, c="r", linewidth=2)
    fig.show()
    if toPDF:
        pdfFileName = f"{prefix}-histogram-{t}.pdf"
        fig.savefig(pdfFileName)#type: ignore


if __name__ == "__main__":
    t = 1000
    plt.rcParams["font.family"] = "Times New Roman"
    plt.rcParams["mathtext.fontset"] = "cm"
    plt.rcParams["mathtext.default"] = "it"
    toPDF = True
    # drawHistogram("Histogram", -150, 150, 0, math.sqrt(t), w=1.8, t=t, toPDF=toPDF)
    # drawHistogram('Uniform',-40,40,0,math.sqrt(t/12),w=.8,t=t, toPDF=toPDF)
    # drawHistogram('Exponential',800,1200,t,math.sqrt(t),t=t,w=.8,toPDF=toPDF)   
