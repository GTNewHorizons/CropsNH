<div align="center"><img src="./docs/logo.png"  alt="Crops NH, IC2 crops reinvented"/></div>

# CropsNH - A complete reimplementation of IC2 crops based on Agricraft.

CropsNH is a complete replacement for the IC2 crop system developed for the [GregTech: New Horizons modpack](https://github.com/GTNewHorizons/GT-New-Horizons-Modpack).

CropsNH significantly improves the gameplay experience of using crops by overhauling the parts of IC2's crop system that made it so annoying to use, while placing greater emphasis on the few elements that were genuinely fun to play around with.

Here's a small summary of the things CropsNH does to improve over its predecessor:
- It puts greater emphasis on player control during the starting process through the use of fertilizer.
- It removes the negative effects of the resistance stat and properly integrates it into the gameplay loop.
- It improves on the original random breeding system by putting a larger emphasis on player control.
- It adds new deterministic methods for breeding all the crops added by CropsNH.
- It puts larger emphasis on breeding and growth requirements to ensure it's well integrated into the gameplay loop.
- It takes great inspiration from bees, overhauling most recipes used to process material crops.
- It adds a more effective multiblock machine to grow crops that can actually compete and surpass a tier-equivalent crop manager.
- It more effectively integrates itself into the GTNH modpack, without compromising its careful balance.
- It completely removes the need to rely on obscure black boxes such as an Open Computer robot to use crops.

# FAQ

## Is CropsNH supported outside of the GTNH modpack?
**<ins>No.</ins>** While CropsNH does check for other mods before adding content related to them, _we do not guarantee support or functionality outside the GTNH modpack._

## Does CropsNH reuse code or assets from IC2?
**<ins>No.</ins>** While some texture assets from IC2 were used as placeholders during the initial phases of the rework, no commit or release to the GTNH CropsNH repo has ever included any IC2 assets.

_If you found something left behind, please notify us immediately so it can be removed promptly._

## What mod is CropsNH based on?
Crops NH began as a fork of **<ins>Agricraft</ins>**. It still resembles it in some key aspects and mechanics, but the underlying codebase has been almost entirely replaced to better integrate with IC2 crops' mechanics. _This means that any addons for the original 1.7.10 release of Agricraft will not work with CropsNH and likely never will._

## Can I still use Open Computers for crops?
**<ins>No.</ins>** Open Computer integration was intentionally removed from the codebase during the initial phases of the rework. This was to force us to create a gameplay loop satisfying enough that using OC for anything crop-related would feel like an absolute waste of time.

_Your valiant crop bots have served you well; may they rest in peace forever in the great farms above._

## What should I do if I find a bug?
There are 2 paths of action depending on what the bug does:
- If it only affects CropsNH, open an issue on the CropsNH repository
  - https://github.com/GTNewHorizons/CropsNH/issues/new
- If it affects something outside of CropsNH. Submit your bug report to the GTNH modpack repository
  - https://github.com/GTNewHorizons/GT-New-Horizons-Modpack

## What should I do if I have a suggestion for the mod?
First, check the [planning tracker issue](https://github.com/GTNewHorizons/CropsNH/issues/26) to prevent duplicate suggestions. If your idea isn't planned or hasn't been suggested, you can submit it by creating a [new issue](https://github.com/GTNewHorizons/CropsNH/issues/new) or join us on the GTNH Discord server to discuss it further.

## Can I contribute to this project?
**<ins>Yes,</ins>** but you'll have to follow some guidelines depending on what you want to contribute.
- Bug fixes and QoL Improvements:
  - Feel free to send us a pull request with a nice summary of your changes so we can review it.
- New Content:
  - Before you start working on anything, get it approved by the GTNH team before making your pull request.
  - You can reach out to us via the GTNH Discord or by creating an issue on the CropsNH repo.
  - Once it's approved, create a draft PR outlining your addition so we can track your progress.
- Compatibility with other mods:
  - Since CropsNH isn't intended to be used outside of the GTNH modpack, all mod integrations for CropsNH should be added directly to CropsNH.
  - If you need to modify another mod to allow cropsNH to better integrate with it, feel free to do so. The GTNH organization maintains custom forks of most mods included in GTNH for that very reason.


---


## License

GTNH Modifications Copyright (c) 2022-2026 the GTNH Team

Licensed under LGPL-3.0 or later - use this however you want, but please give back any modifications

## Original License

The MIT License (MIT)

Copyright (c) 2014 InfinityRaider

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

